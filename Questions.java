import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;
import java.util.Random;
import java.util.Arrays;

public class Questions {
    JButton right, wrong, next, res;
    JTextArea score, qcnt, qa;
    Random rand;
    int w = 0;
    int cntq = 5;
    int cntca = 0;
    int cnt = 0;
    String[] qt = { "Please answer questions to collect ✻ \n Ready ?", "9 x 9 = 80", "90 x 7 = 630", "6 x 50 = 500" };
    InsertQuestions iq;

    public int getScore() {
        return cntca;
    }

    public int getQcount() {
        return cntq;
    }

    public String[] getQlist() {
        return this.qt;
    }

    public String[] importQlist() {
        this.qt = iq.getQlists();
        return this.qt;
    }

    public JPanel game() {
        iq = new InsertQuestions();
        JPanel g = new JPanel();
        g.setLayout(new BoxLayout(g, BoxLayout.Y_AXIS));
        JPanel gpl = new JPanel();
        gpl.setLayout(new BoxLayout(gpl, BoxLayout.Y_AXIS));
        JPanel butt = new JPanel();
        butt.setLayout(new BoxLayout(butt, BoxLayout.X_AXIS));

        score = new JTextArea("q", 1, 15);
        score.setText("Score : " + cntca + "/" + cntq);
        score.setEditable(false);
        score.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));

        qcnt = new JTextArea("q", 1, 15);
        qcnt.setText("Question " + (cnt + 1) + " :");
        qcnt.setEditable(false);
        qcnt.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));

        qa = new JTextArea(qt[0], 10, 30);
        qa.setEditable(false);
        qa.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));

        // Learn from: http://www.zentut.com/java-swing/java-swing-cardlayout/
        class al implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                String cm = e.getActionCommand();
                Random rand = new Random();
                if (cm == "NEXT") {
                    qt = getQlist();
                    right.setEnabled(true);
                    wrong.setEnabled(true);
                    next.setEnabled(false);
                    res.setEnabled(true);
                    next.setText("Next");
                    qcnt.setText("Question " + (cnt + 1) + " :");
                    w = rand.nextInt((qt.length - 1 - 1) + 1) + 1;
                    while (qt[w] == "" || qt[w] == null) {
                        w = rand.nextInt((qt.length - 1 - 1) + 1) + 1;
                    }
                    if (w > 1 && w % 2 == 0) {
                        right.setActionCommand("RIGHT");
                        wrong.setActionCommand("WRONG");
                    } else {
                        right.setActionCommand("WRONG");
                        wrong.setActionCommand("RIGHT");
                    }
                    qa.setText(qt[w]);
                } else if (cm == "RIGHT") {
                    qa.setText("CORRECT ANSWER!");
                    right.setEnabled(false);
                    wrong.setEnabled(false);
                    next.setEnabled(true);
                    cntca++;
                    cnt++;
                    score.setText("Score : " + cntca + "/" + cntq);
                    if (cnt >= cntq) {
                        next.setActionCommand("END");
                        next.setText("End");
                    }
                } else if (cm == "WRONG") {
                    qa.setText("WRONG ANSWER!");
                    right.setEnabled(false);
                    wrong.setEnabled(false);
                    next.setEnabled(true);
                    cnt++;
                    score.setText("Score : " + cntca + "/" + cntq);
                    if (cnt >= cntq) {
                        next.setActionCommand("END");
                        next.setText("End");
                    }
                } else if (cm == "END") {
                    qa.setText("Game End! \nYou answered " + cnt + " questions.\n" + "Your score is " + cntca + " .");
                    right.setEnabled(false);
                    wrong.setEnabled(false);
                    next.setEnabled(true);
                    res.setEnabled(false);
                    next.setActionCommand("NEXT");
                    next.setText("Restart");
                    w = 0;
                    cntca = 0;
                    cnt = 0;
                    score.setText("Score : " + cntca + "/" + cntq);
                    qcnt.setText("Question " + (cnt + 1) + " :");
                    qt = importQlist();
                }
            }
        }
        al AL = new al();

        right = new JButton("True");
        right.addActionListener(AL);
        wrong = new JButton("False");
        wrong.addActionListener(AL);
        next = new JButton("Start");
        next.setActionCommand("NEXT");
        next.addActionListener(AL);
        res = new JButton("Restart");
        res.setActionCommand("END");
        res.addActionListener(AL);
        right.setEnabled(false);
        wrong.setEnabled(false);
        res.setEnabled(false);

        g.add(score);
        gpl.add(qcnt);
        gpl.add(qa);
        butt.add(right);
        butt.add(wrong);
        butt.add(next);
        butt.add(res);
        gpl.add(butt);
        g.add(gpl);

        return g;
    }
}
