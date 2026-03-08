package Functions;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

public class PrintError {

    public static void printErro(Throwable e) {

        Map<Class<? extends Throwable>, String> mensagemDeErro = new HashMap<>();

        mensagemDeErro.put(InputMismatchException.class, "tipo de dado inválido digitado");
        mensagemDeErro.put(NumberFormatException.class, "erro na digitação de algum número");
        mensagemDeErro.put(ArithmeticException.class, "erro na divisão, é impossível dividir por 0");
        mensagemDeErro.put(NullPointerException.class, "alguma variável usada não foi definida");
        mensagemDeErro.put(ClassCastException.class, "tipo de dado inserido não é válido");
        mensagemDeErro.put(ArrayIndexOutOfBoundsException.class, "índice do array fora do limite");
        mensagemDeErro.put(StringIndexOutOfBoundsException.class, "índice da string fora do limite");
        mensagemDeErro.put(IllegalArgumentException.class, "argumento inválido passado para o método");
        mensagemDeErro.put(IllegalStateException.class, "estado ilegal do objeto");
        mensagemDeErro.put(UnsupportedOperationException.class, "operação não suportada");
        mensagemDeErro.put(DateTimeParseException.class, "formato de data inválido (use: AAAA-MM-DD)");
        mensagemDeErro.put(OutOfMemoryError.class, "memória cheia");
        mensagemDeErro.put(StackOverflowError.class, "estouro da pilha de memória");
        mensagemDeErro.put(SecurityException.class, "violação de segurança");
        mensagemDeErro.put(InterruptedException.class, "execução interrompida");

        for (Map.Entry<Class<? extends Throwable>, String> erroDetectado : mensagemDeErro.entrySet()) {
            if (erroDetectado.getKey().isInstance(e)) {
                System.out.println(erroDetectado.getValue());
                return;
            }
        }

        System.out.println("erro inesperado: " + e.getMessage());
    }
}
