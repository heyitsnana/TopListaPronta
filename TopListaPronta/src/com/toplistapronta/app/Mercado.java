package TopLista;

import java.util.ArrayList;
import java.util.List;

public class Mercado {
    private String nome;
    private String cep;
    private String complemento;
    private List<Localizacao> localizacoes;

    public static class Localizacao {
        private String endereco;
        private String numero;
        private double latitude;
        private double longitude;

        public Localizacao(String endereco, String numero, double latitude, double longitude) {
            this.endereco = endereco;
            this.numero = numero;
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public String getEndereco() {
            return endereco;
        }
        public void setEndereco(String endereco) {
            this.endereco = endereco;
        }
        public String getNumero() {
            return numero;
        }
        public void setNumero(String numero) {
            this.numero = numero;
        }
        public double getLatitude() {
            return latitude;
        }
        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }
        public double getLongitude() {
            return longitude;
        }
        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }
    }

    public Mercado(String nome, String cep, String complemento) {
        this.nome = nome;
        this.cep = cep;
        this.complemento = complemento;
        this.localizacoes = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCep() {
        return cep;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }
    public String getComplemento() {
        return complemento;
    }
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
    public List<Localizacao> getLocalizacoes() {
        return localizacoes;
    }

    public void addLocalizacao(String endereco, String numero, double latitude, double longitude) {
        this.localizacoes.add(new Localizacao(endereco, numero, latitude, longitude));
    }

    public void updateLocalizacao(int index, String endereco, String numero, double latitude, double longitude) {
        Localizacao loc = this.localizacoes.get(index);
        loc.setEndereco(endereco);
        loc.setNumero(numero);
        loc.setLatitude(latitude);
        loc.setLongitude(longitude);
    }

    public void removeLocalizacao(int index) {
        this.localizacoes.remove(index);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Mercado: ").append(nome).append("\n");
        for (Localizacao loc : localizacoes) {
            sb.append("Endereço: ").append(loc.getEndereco())
              .append(" | Nº: ").append(loc.getNumero())
              .append(" | Latitude: ").append(loc.getLatitude())
              .append(" | Longitude: ").append(loc.getLongitude())
              .append("\n");
        }
        return sb.toString();
    }
}

