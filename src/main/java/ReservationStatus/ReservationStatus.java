package ReservationStatus;

import jakarta.persistence.EntityManager;
import org.ONE.models.Reservations;
import org.ONE.models.ENUM.Status;

import java.util.Scanner;

public class ReservationStatus {

    private EntityManager em = null;

    public ReservationStatus(EntityManager em) {
        this.em = this.em;
    }

    public Status escolherStatus() {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== ALTERAR STATUS ===");
        System.out.println("1 - Confirmado");
        System.out.println("2 - Cancelado");
        System.out.print("Escolha: ");

        int opcao = sc.nextInt();

        switch (opcao) {
            case 1:
                return Status.CONFIRMADA;
            case 2:
                return Status.CANCELADA;
            default:
                System.out.println("Opção inválida!");
                return Status.pendente;
        }
    }

    // 🔹 Atualizar status no banco
    public void atualizar(Long idReserva) {

        try {
            em.getTransaction().begin();

            Reservations reserva = em.find(Reservations.class, idReserva);

            if (reserva != null) {

                Status novoStatus = escolherStatus();

                reserva.setStatus(novoStatus);

                System.out.println("Status atualizado para: " + novoStatus);

            } else {
                System.out.println("⚠Reserva não encontrada.");
            }

            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Erro ao atualizar status:");
            e.printStackTrace();
        }
    }
}