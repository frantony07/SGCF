package Functions;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

public class PrintError {

    public static void printErro(Throwable e) {

        Map<Class<? extends Throwable>, String> mensagemDeErro = new HashMap<>();

        mensagemDeErro.put(InputMismatchException.class, "Tipo de dado inválido digitado");
        mensagemDeErro.put(NumberFormatException.class, "Erro na digitação de algum número");
        mensagemDeErro.put(ArithmeticException.class, "Erro na divisão, é impossível dividir por 0");
        mensagemDeErro.put(NullPointerException.class, "Alguma variável usada não foi definida");
        mensagemDeErro.put(ClassCastException.class, "Tipo de dado inserido não é válido");
        mensagemDeErro.put(ArrayIndexOutOfBoundsException.class, "índice do array fora do limite");
        mensagemDeErro.put(StringIndexOutOfBoundsException.class, "índice da string fora do limite");
        mensagemDeErro.put(IllegalArgumentException.class, "Argumento inválido passado para o método");
        mensagemDeErro.put(IllegalStateException.class, "Estado ilegal do objeto");
        mensagemDeErro.put(UnsupportedOperationException.class, "Operação não suportada");
        mensagemDeErro.put(DateTimeParseException.class, "Formato de data inválido (use: AAAA-MM-DD)");
        mensagemDeErro.put(OutOfMemoryError.class, "Memória cheia");
        mensagemDeErro.put(StackOverflowError.class, "Estouro da pilha de memória");
        mensagemDeErro.put(SecurityException.class, "Violação de segurança");
        mensagemDeErro.put(InterruptedException.class, "Execução interrompida");

        for (Map.Entry<Class<? extends Throwable>, String> erroDetectado : mensagemDeErro.entrySet()) {
            if (erroDetectado.getKey().isInstance(e)) {
                System.out.println(erroDetectado.getValue());
                return;
            }
        }

        System.out.println("Erro inesperado: " + e.getMessage());
    }
}
