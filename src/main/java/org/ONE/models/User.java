package org.ONE.models;

import jakarta.persistence.*;

@Entity(name = "user_account")
public class User {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    @Column(name = "user_name" , nullable = false , length = 35 , unique = true)
    private String userName;

    @Column(name = "user_password" , nullable = false , length = 35)
    private String userPassword;

     @OneToOne
     @JoinColumn(name = "fk_funcionario_id")
     private Funcionario funcionarioId;



    public User(Funcionario funcionarioId, String userName, String userPassword) {
        this.funcionarioId = funcionarioId;

        this.userName = userName;
        this.userPassword = userPassword;
    }

    public User() {
    }

    public Funcionario getFuncionarioId() {
        return funcionarioId;
    }

    public void setFuncionarioId(Funcionario funcionarioId) {
        this.funcionarioId = funcionarioId;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
