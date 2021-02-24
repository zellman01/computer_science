@ECHO off
:: Unary operators
prog4.exe 15 !
echo.
prog4.exe 15 F
echo.
prog4.exe -1 !
echo.
prog4.exe -1 F
echo.
prog4.exe 10 !
echo.
prog4.exe 10 F
echo.

:: Binary, first set
prog4.exe 12 + 15
prog4.exe 12 - 15
prog4.exe 12 * 15
prog4.exe 12 / 15
prog4.exe 12 %% 15
prog4.exe 12 P 15

:: Binary, second set
prog4.exe 15 + 12
prog4.exe 15 - 12
prog4.exe 15 * 12
prog4.exe 15 / 12
prog4.exe 15 %% 12
prog4.exe 15 P 12

:: Binary, third set
prog4.exe -3.89 + 16.8865
prog4.exe -3.89 - 16.8865
prog4.exe -3.89 * 16.8865
prog4.exe -3.89 / 16.8865
prog4.exe -3.89 %% 16.8865
prog4.exe -3.89 P 16.8865

PAUSE