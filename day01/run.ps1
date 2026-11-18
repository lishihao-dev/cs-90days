# Day 1 build & run helper (ASCII only on purpose: Windows PowerShell 5.1 reads
# .ps1 as ANSI when there is no BOM, so Chinese text in this file would be garbled).
#
# Usage (inside D:\cs-90days\day01):
#   powershell -NoProfile -ExecutionPolicy Bypass -File .\run.ps1
#   powershell -NoProfile -ExecutionPolicy Bypass -File .\run.ps1 all
#   powershell -NoProfile -ExecutionPolicy Bypass -File .\run.ps1 LeapYear 2024
#
# Why this script exists:
#   On this machine `java` on PATH is Oracle JRE 1.8 while `javac` is .jdks\jdk-25,
#   so a plain javac + java pair fails with UnsupportedClassVersionError
#   (class file version 69.0). This script pins both to the same JDK 25.

$ErrorActionPreference = 'Continue'

$JdkHome = 'C:\Users\31995\.jdks\jdk-25.0.4.1+1'
if (-not (Test-Path $JdkHome)) {
    Write-Host "JDK not found: $JdkHome" -ForegroundColor Red
    exit 1
}

$env:JAVA_HOME = $JdkHome
$env:Path = "$JdkHome\bin;" + $env:Path

chcp 65001 | Out-Null
try {
    [Console]::OutputEncoding = [System.Text.Encoding]::UTF8
} catch {
    Write-Host "warn: cannot switch console to UTF-8" -ForegroundColor Yellow
}

Write-Host '=== env ===' -ForegroundColor Cyan
$ver = & "$JdkHome\bin\java" -version 2>&1
$ver | ForEach-Object { Write-Host "  $_" }
Write-Host "  JAVA_HOME = $env:JAVA_HOME"

$Root = Split-Path -Parent $MyInvocation.MyCommand.Definition
Set-Location $Root

$Out = Join-Path $Root 'out'
if (Test-Path $Out) { Remove-Item -Recurse -Force $Out }
New-Item -ItemType Directory -Force -Path $Out | Out-Null

Write-Host ''
Write-Host '=== compile ===' -ForegroundColor Cyan
& javac -encoding UTF-8 -d $Out HelloWorld.java Temperature.java Bmi.java LeapYear.java
if ($LASTEXITCODE -ne 0) {
    Write-Host "compile failed, exit $LASTEXITCODE" -ForegroundColor Red
    exit 1
}
Get-ChildItem $Out -Filter *.class | ForEach-Object {
    Write-Host ('  ' + $_.Name + '  ' + $_.Length + ' bytes')
}

$JavaArgs = @('-Dfile.encoding=UTF-8', '-Dstdout.encoding=UTF-8', '-Dstderr.encoding=UTF-8')

function Invoke-One {
    param([string]$Cls, [string[]]$ClsArgs)
    Write-Host ''
    Write-Host ("--- $Cls ---") -ForegroundColor Yellow
    $callArgs = $JavaArgs + @('-cp', $Out, $Cls) + $ClsArgs
    & java @callArgs
    if ($LASTEXITCODE -ne 0) {
        Write-Host ("  [exit code $LASTEXITCODE]") -ForegroundColor Red
    }
}

$Target = 'all'
$Rest = @()
if ($args.Count -ge 1) {
    $Target = $args[0]
    if ($args.Count -ge 2) { $Rest = $args[1..($args.Count - 1)] }
}

Write-Host ''
Write-Host '=== run ===' -ForegroundColor Cyan

if ($Target -eq 'all') {
    Invoke-One 'HelloWorld' @()
    Invoke-One 'Temperature' @()
    Invoke-One 'Bmi' @()
    Invoke-One 'LeapYear' @()

    Write-Host ''
    Write-Host '--- with arguments ---' -ForegroundColor Yellow
    & java @JavaArgs -cp $Out Temperature 98.6
    & java @JavaArgs -cp $Out Temperature c 37
    & java @JavaArgs -cp $Out Bmi 70 1.75
    & java @JavaArgs -cp $Out LeapYear 1900
    & java @JavaArgs -cp $Out LeapYear 2000
} else {
    Invoke-One $Target $Rest
}
