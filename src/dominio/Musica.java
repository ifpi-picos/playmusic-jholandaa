package dominio;

public class Musica {
  private String nome;
  private String genero;
  private String arquivoAudio;
  private int duracao;

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getGenero() {
    return genero;
  }

  public void setGenero(String genero) {
    this.genero = genero;
  }

  public int getDuracao() {
    return duracao;
  }

  public void setDuracao(int duracao) {
    this.duracao = duracao;
  }

  public String getArquivoAudio() {
    return arquivoAudio;
  }

  public void setArquivoAudio(String arquivoAudio) {
    this.arquivoAudio = arquivoAudio;
  }

}