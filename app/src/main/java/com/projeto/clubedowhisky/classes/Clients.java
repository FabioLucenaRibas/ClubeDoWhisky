package com.projeto.clubedowhisky.classes;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Renato on 20/11/2017.
 */

public class Clients {

    private int id;
    private String bairro;
    private String celular;
    private String cep;
    private String cidade;
    private String complemento;
    private String cpf;
    @SerializedName("dt_nascimento")
    private String dataNascimento;
    private String endereco;
    private String numero;
    private String sexo;
    private String telefone;
    private Users user;
}
