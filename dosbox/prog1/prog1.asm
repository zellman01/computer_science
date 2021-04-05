;  Sample basic assembler program.
;    Type in the program, then save it as prog1.asm
;    Start DOSBox.  If you edited the config file, you should be in the directory
;        where you saved prog1.asm.
;    Type the command DIR at the prompt and see if your prog1.asm is listed. 
;    Use    t:> tasm prog1.asm    to assemble (compile) your program.
;    Type the command DIR again to see if you now have the file prog1.obj.
;    Use    t:> tlink prog1.obj   to link your program.
;    Type the command DIR again to see if you now have the file prog1.exe.
;    Use    t:> prog1.exe      to run your program

;    The file extensions in the commands above are optional.
;    (e.g., tasm prog1 is ok if the file is named prog1.asm).
;    Commands may be entered in upper or lower case.
;
;----------------------------------------------------------------------- STACK SEGMENT
MyStack SEGMENT STACK              ; Keyword "STACK" is required!

        DW 256 DUP (?)             ; At least a small stack is needed for interrupt
                                   ; handling.

MyStack ENDS                       ; End Segment

;------------------------------------------------------------------------ DATA SEGMENT
MyData SEGMENT
    dog         DB  10, 11, 12     ; These examples reserve memory,
    cat         DB  'Hello', 0     ; but are not used in this program.
                                   ; To reference the data segment, you
                                   ; must set up the DS register!
    rat         DW  0C32Ah         ; Initialize with hex C32A.

    ScreenMemSeg EQU 0B800h        ; Segment for video RAM.

MyData ENDS                        ; End Segment

;------------------------------------------------------------------------ CODE SEGMENT
MyCode SEGMENT
        ASSUME CS:MyCode, DS:MyData    ; Tell the assembler what segments the segment 
                                       ; registers refer to in the following code.

mainProg  PROC                     ; This label will serve as the "user transfer address"
                                   ; because it is specified in the END directive at the
                                   ; bottom of this source file.

    MOV     AX, MyData             ; Make DS address our data segment.  Two statements
    MOV     DS, AX                 ; are required because it is illegal to move
                                   ; immediate data directly into a segment register.
                                   ; This is essential if your program refers to any
                                   ; of symbols declared with DB, DW, or DD in the
                                   ; data segment of your program.

    MOV     AX, ScreenMemSeg       ; Make ES segment register address video memory.
    MOV     ES, AX

    MOV     ES:[542], BYTE PTR 'W' ; Display 'W' on row 3, col 31 of the console.
                                   ;        (3*80 + 31)*2 = 542
                                   ; Could have used
                                   ;   MOV  ES:[(3*80 + 31)*2], BYTE PTR 'A'

    MOV     ES:[543], BYTE PTR 01111100b     ; Color it bright red on gray.

    MOV     AH, 4Ch                ; These two instructions use a DOS interrupt which
    INT     21h                    ; "calls" a kernel routine to release the memory for
                                   ; this program and then return control to DOS.
                                   ; This is the logical end of the program.

mainProg ENDP                      ; End of the main proc

MyCode ENDS                        ; End Segment

;-------------------------------------------------------------------------------------
END mainProg                       ; This END marks the physical end of the source code
                                   ; file.  The label specified will be the
                                   ; "user transfer address" where execution will begin.