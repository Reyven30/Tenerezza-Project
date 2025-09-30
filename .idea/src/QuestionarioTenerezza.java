import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuestionarioTenerezza {
    private JFrame frame;
    private JPanel panel;
    private JLabel questionLabel;
    private JButton option1Button;
    private JButton option2Button;
    private JButton option3Button;
    private JButton option4Button;

    private String[] questions = {
            "Quando un amico ha bisogno di aiuto, come reagisci?",
            "Qual è il modo migliore per mostrare affetto a qualcuno?",
            "Se vedi una persona in difficoltà, cosa fai?",
            "Quale di queste situazioni ti colpisce di più?",
            "In che modo il perdono influisce sulle relazioni?"
    };

    private String[][] options = {
            {"Offro il mio supporto senza esitazioni",
                    "Ascolto e cerco di capire",
                    "Decido di restare neutrale",
                    "Preferisco non immischiarmi"},
            {"Con piccoli gesti quotidiani",
                    "Con parole gentili e incoraggianti",
                    "Con doni materiali",
                    "Con la mia presenza nei momenti difficili"},
            {"Intervengo subito per aiutarlo",
                    "Chiedo se ha bisogno di qualcosa",
                    "Contatto qualcuno che può aiutare",
                    "Mi allontano per non creare imbarazzo"},
            {"Una persona che perde il lavoro",
                    "Un genitore solo con bambini",
                    "Un anziano che vive da solo",
                    "Un amico che affronta una malattia"},
            {"È essenziale, aiuta a ricostruire",
                    "È difficile ma necessario",
                    "Non sempre è possibile",
                    "Non lo considero importante"}
    };

    private int currentQuestionIndex = 0;
    private int totalScore = 0;

    public QuestionarioTenerezza() {
        // Crea il frame principale
        frame = new JFrame("Questionario Tenerezza");
        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Creazione dei componenti
        questionLabel = new JLabel();
        option1Button = new JButton();
        option2Button = new JButton();
        option3Button = new JButton();
        option4Button = new JButton();

        // Aggiunta dei pulsanti al pannello
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 2));
        buttonPanel.add(option1Button);
        buttonPanel.add(option2Button);
        buttonPanel.add(option3Button);
        buttonPanel.add(option4Button);

        panel.add(questionLabel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);

        frame.add(panel);
        frame.setSize(700, 600);
        frame.setLocationRelativeTo(null);  // Centra il frame sullo schermo
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        loadQuestion();

        option1Button.addActionListener(new QuestionarioActionListener(0));
        option2Button.addActionListener(new QuestionarioActionListener(1));
        option3Button.addActionListener(new QuestionarioActionListener(2));
        option4Button.addActionListener(new QuestionarioActionListener(3));
    }

    private void loadQuestion() {
        if (currentQuestionIndex < questions.length) {
            questionLabel.setText(questions[currentQuestionIndex]);
            option1Button.setText(options[currentQuestionIndex][0]);
            option2Button.setText(options[currentQuestionIndex][1]);
            option3Button.setText(options[currentQuestionIndex][2]);
            option4Button.setText(options[currentQuestionIndex][3]);
        } else {
            showResult();
        }
    }

    private void updateScore(int optionIndex) {
        switch (optionIndex) {
            case 0: totalScore += 10; break;
            case 1: totalScore += 8; break;
            case 2: totalScore += 5; break;
            case 3: totalScore += 2; break;
        }
        currentQuestionIndex++;
        loadQuestion();
    }

    private void showResult() {
        frame.setVisible(false); // Nascondi il frame del questionario
        JFrame resultFrame = new JFrame("Risultato del Questionario");
        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new BorderLayout());

        JLabel scoreLabel = new JLabel("Il tuo punteggio di tenerezza: " + totalScore);
        JLabel descriptionLabel = new JLabel(getDescription(totalScore));

        resultPanel.add(scoreLabel, BorderLayout.NORTH);
        resultPanel.add(descriptionLabel, BorderLayout.CENTER);

        resultFrame.add(resultPanel);
        resultFrame.setSize(700, 600); // Imposta la dimensione del frame dei risultati
        resultFrame.setLocationRelativeTo(null); // Centra il frame dei risultati
        resultFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        resultFrame.setVisible(true);
    }

    private String getDescription(int score) {
        if (score >= 40) {
            return "Sei una persona molto tenera e sensibile! La tua capacità di capire e rispettare le emozioni altrui è notevole.";
        } else if (score >= 30) {
            return "Hai un buon livello di tenerezza! Dimostri di avere empatia e voglia di sostenere gli altri nelle difficoltà.";
        } else if (score >= 20) {
            return "Mostri un certo grado di tenerezza. Hai potenziale per incrementare la tua capacità di connetterti emotivamente.";
        } else {
            return "Potresti lavorare sulla tua tenerezza! Riflettere sulle tue interazioni ti aiuterà a comprendere meglio le emozioni altrui.";
        }
    }

    public class QuestionarioActionListener implements ActionListener {
        private int optionIndex;

        public QuestionarioActionListener(int optionIndex) {
            this.optionIndex = optionIndex;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            updateScore(optionIndex);
        }
    }

}