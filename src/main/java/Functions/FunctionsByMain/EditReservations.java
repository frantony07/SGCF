package Functions.FunctionsByMain;

import Functions.PrintError;
import Functions.ValidateNumber;
import org.ONE.model.entity.ENUM.Status;
import org.ONE.model.entity.Reservations;
import org.ONE.model.entity.User;
import org.ONE.model.services.ReservationsService;

import java.util.List;
import java.util.Scanner;

public class EditReservations {
    ReservationsService reservations = new ReservationsService();

    public void main(User user) {
        try {
            System.out.println("____________________________");
            List<Reservations> pendentes = reservations.findAll().stream()
                .filter(r -> r.getStatus() == Status.pendente)
                .toList();

            if (pendentes.isEmpty()) {
                System.out.println("Não há reservas pendentes para alterar.");
                System.out.println("____________________________");
                return;
            }

            pendentes.forEach(r ->
                System.out.printf("[ID: %d] %s | Cliente: %s | Passeio: %s | Status: %s | R$ %.2f%n",
                    r.getId(),
                    r.getDate(),
                    r.getCliente().getName(),
                    r.getTour().getNameOfTour(),
                    r.getStatus(),
                    r.getValue())
            );
            System.out.println("____________________________");

            System.out.println("Digite o ID da reserva que deseja alterar:");
            Scanner sc = new Scanner(System.in);
            if (!sc.hasNextLong()) {
                System.out.println("Tipo de dado inválido digitado");
                sc.next();
                return;
            }
            long reservationId = sc.nextLong();

            Reservations reservation = reservations.findById(reservationId);

            if (reservation == null) {
                System.out.println("Reserva não encontrada.");
                return;
            }

            if (reservation.getStatus() != Status.pendente) {
                System.out.println("Apenas reservas com status 'pendente' podem ser alteradas.");
                return;
            }

            System.out.println("Status atual: " + reservation.getStatus());
            System.out.println("Selecione o novo estado:");
            System.out.println("1. Confirmada");
            System.out.println("2. Cancelada");

            int choice = ValidateNumber.validateINT(2);

            Status novoStatus = switch (choice) {
                case 1 -> Status.CONFIRMADA;
                case 2 -> Status.CANCELADA;
                default -> throw new IllegalStateException("Opção inválida: " + choice);
            };

            reservation.setStatus(novoStatus);
            reservations.updateRecorde(reservation);

            System.out.println("Status da reserva alterado para " + novoStatus);
        } catch (Exception e) {
            PrintError.printErro(e);
        }
    }
}
