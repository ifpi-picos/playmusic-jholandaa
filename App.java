import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import dominio.Album;
import dominio.Artista;
import dominio.Musica;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        // Criação das músicas
        Musica musica1 = new Musica();
        musica1.setNome("Californication");
        musica1.setDuracao(120);
        musica1.setArquivoAudio("./assets/Red-Hot-Chili-Peppers-Californication.wav");
        musica1.setGenero("Rock");

        Musica musica2 = new Musica();
        musica2.setNome("Creep");
        musica2.setDuracao(120);
        musica2.setArquivoAudio("./assets/Creep - Radiohead.wav");
        musica2.setGenero("Rock");

        // Criação do Álbum
        Album album1 = new Album();
        album1.setNome("Primeiro album");
        album1.setAno(2000);
        album1.addMusica(musica1);
        album1.addMusica(musica2);

        // Criação do Artista
        Artista redHot = new Artista();
        redHot.setNome("Red Hot Chili Peppers");
        redHot.addAlbum(album1);

        // Configuração do Player
        System.out.println("Abrindo o PlayMusic");
        AudioPlayer player = new AudioPlayer();

        List<Musica> musicas = redHot.getAlbuns().get(0).getMusicas();
        final int[] musicaAtual = {0}; // Índice da música atual

        ImageIcon musicIcon = new ImageIcon("./assets/musica.png"); // Caminho para a imagem
        JLabel imageLabel = new JLabel(musicIcon);
        imageLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        // Janela principal
        JFrame frame = new JFrame("PlayMusic");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        // Rótulo que exibe a música atual
        JLabel labelMusicaAtual = new JLabel("Tocando: " + musicas.get(musicaAtual[0]).getNome());
        labelMusicaAtual.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        // Função para atualizar e tocar a música
        Runnable atualizarMusica = () -> {
            player.stopAudio(); // Para a música anterior
            player.loadAudio(musicas.get(musicaAtual[0]).getArquivoAudio());
            player.playAudio();
            labelMusicaAtual.setText("Tocando: " + musicas.get(musicaAtual[0]).getNome());
        };

        // Botão Play
        JButton playButton = new JButton("Tocar");
        playButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        playButton.addActionListener(e -> {
            player.loadAudio(musicas.get(musicaAtual[0]).getArquivoAudio());
            player.playAudio();
            labelMusicaAtual.setText("Tocando: " + musicas.get(musicaAtual[0]).getNome());
        });

        // Botão Próxima Música
        JButton nextButton = new JButton("Próxima");
        nextButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        nextButton.addActionListener(e -> {
            musicaAtual[0] = (musicaAtual[0] + 1) % musicas.size(); // Avança e reinicia no início
            atualizarMusica.run();
        });

        // Botão Música Anterior
        JButton prevButton = new JButton("Anterior");
        prevButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        prevButton.addActionListener(e -> {
            musicaAtual[0] = (musicaAtual[0] - 1 + musicas.size()) % musicas.size(); // Volta e reinicia no fim
            atualizarMusica.run();
        });

        // Botão Pause/Resume
        JButton pauseResumeButton = new JButton("Pause");
        pauseResumeButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        pauseResumeButton.addActionListener(e -> {
            if (player.isPaused) {
                player.resumeAudio();
                pauseResumeButton.setText("Pause");
            } else {
                player.pauseAudio();
                pauseResumeButton.setText("Resume");
            }
        });

        // Adiciona componentes à interface
        frame.add(labelMusicaAtual);
        frame.add(playButton);
        frame.add(prevButton);
        frame.add(pauseResumeButton);
        frame.add(nextButton);

        frame.setVisible(true);

        // Fecha o áudio ao encerrar o programa
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (player.audioClip != null) {
                player.audioClip.close();
            }
        }));
    }
}
