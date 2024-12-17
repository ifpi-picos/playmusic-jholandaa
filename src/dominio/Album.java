package dominio;

import java.util.ArrayList;
import java.util.List;

public class Album {
  private String nome;
  private int ano;
  private List<Musica> musicas = new ArrayList<>();

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public int getAno() {
    return ano;
  }

  public void setAno(int ano) {
    this.ano = ano;
  }

  public List<Musica> getMusicas() {
    return musicas;
  }

  public void addMusica(Musica musica) {
    musicas.add(musica);
  }

  public void addMusicas(List<Musica> musicas) {
    this.musicas.addAll(musicas);
  }

}
