@ControllerAdvice
public class MyExceptionHandler {

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<String> handler() {
    return ResponseEntity.status(400).body("특정 에러시 발동");
  }

} 
