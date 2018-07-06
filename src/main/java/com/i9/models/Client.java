package com.i9.models;

public class Client {
    private String CNPJCPF;

    private String name;

    private String type;

    public Client(String CNPJCPF, String name, String type) {
        this.CNPJCPF = CNPJCPF;
        this.name = name;
        this.type = type;
    }

    public Client() {}

    public String getCNPJCPF() {
        return CNPJCPF;
    }

    public void setCNPJCPF(String CNPJCPF) {
        this.CNPJCPF = CNPJCPF;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
