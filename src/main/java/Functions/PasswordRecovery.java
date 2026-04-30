package Functions;

import org.ONE.services.EmailServices;
import org.ONE.services.UserServices;

import java.util.Scanner;

public class PasswordRecovery {

    private final UserServices userServices = new UserServices();
    private final EmailServices emailService = new EmailServices();

    public void iniciarRecuperacao() {
        Scanner sc = new Scanner(System.in);

        System.out.println("\nRECUPERAÇÃO DE SENHA");
        System.out.println("Digite o e-mail cadastrado:");
        String email = sc.nextLine().trim();

        boolean tokenGerado = userServices.generatePasswordResetToken(email);

        if (tokenGerado) {
            try {
                String token = userServices.getTokenByEmail(email);
                emailService.sendPasswordResetEmail(email, token);
            } catch (Exception e) {
                System.out.println("Erro ao enviar o e-mail. Verifique se o MailHog está rodando na porta 1025.");
                return;
            }
        }

        System.out.println("\nDigite o código recebido no e-mail:");

        boolean sucesso = false;
        for (int tentativa = 1; tentativa <= 3; tentativa++) {
            String tokenDigitado = sc.nextLine().trim();

            System.out.println("Digite a nova senha (mínimo 4 caracteres):");
            String novaSenha = sc.nextLine().trim();

            if (novaSenha.length() < 4 || novaSenha.length() > 35) {
                System.out.println("Senha inválida. Deve ter minimo 4  caracteres.");
                if (tentativa < 3) System.out.println("Tente novamente. Tentativa " + (tentativa + 1) + " de 3.");
                continue;
            }

            System.out.println("Confirme a nova senha:");
            String confirmacao = sc.nextLine().trim();

            if (!novaSenha.equals(confirmacao)) {
                System.out.println("As senhas não conferem.");
                if (tentativa < 3) System.out.println("Tente novamente. Tentativa " + (tentativa + 1) + " de 3.");
                continue;
            }

            sucesso = userServices.validateAndResetPassword(tokenDigitado, novaSenha);

            if (sucesso) {
                System.out.println("Senha alterada com sucesso! Faça login com a nova senha.\n");
                return;
            } else {
                System.out.println("Código inválido ou expirado.");
                if (tentativa < 3) System.out.println("Tente novamente. Tentativa " + (tentativa + 1) + " de 3.");
            }
        }

        if (!sucesso) {
            System.out.println("Número máximo de tentativas atingido. Solicite um novo código.\n");
        }
    }
}
