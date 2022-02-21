import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;
import java.util.Random;
import java.util.Arrays;

class InsertQuestions {
    JButton ins, clear, find, at, af;
    JTextField Qnum, Qcontent;
    JTextArea QList;
    JScrollPane scl;
    JPopupMenu ans;
    JLabel l1, l2, l3, l4;
    String[] q, q1;
    String allq, an, up;
    int ut = 0;

    public JPanel insert() {
        Questions qu = new Questions();
        JPanel fnum = new JPanel();
        fnum.setLayout(new BoxLayout(fnum, BoxLayout.X_AXIS));
        JPanel btns2 = new JPanel();
        btns2.setLayout(new BoxLayout(btns2, BoxLayout.X_AXIS));
        JPanel showinsertq = new JPanel();
        showinsertq.setLayout(new BoxLayout(showinsertq, BoxLayout.Y_AXIS));
        JPanel btns = new JPanel();
        btns.setLayout(new BoxLayout(btns, BoxLayout.X_AXIS));
        JPanel inserting = new JPanel();
        inserting.setLayout(new BoxLayout(inserting, BoxLayout.Y_AXIS));
        // ------ +
        JPanel showallq = new JPanel();
        showallq.setLayout(new BoxLayout(showallq, BoxLayout.Y_AXIS));
        // ------ =
        JPanel allinterface = new JPanel();
        allinterface.setLayout(new BoxLayout(allinterface, BoxLayout.X_AXIS));

        q = qu.getQlist();
        allq = "";
        for (int r = 0; r < q.length; r++) {
            if (r % 2 == 0) {
                allq = allq + r + " :\n" + q[r] + "\n\nAnswer is [True]\n\n";
            } else if (r == 1) {
                allq = allq + r + " :\n" + q[r] + "\n\nAnswer is [False]\n\n";
            } else if (r == 0) {
                allq = allq + r + " :\n" + q[r] + "\n\n";
            } else {
                allq = allq + r + " :\n" + q[r] + "\n\nAnswer is [False]\n\n";
            }
        }

        l1 = new JLabel("Question Number : ");
        l2 = new JLabel("Question Content : ");
        l3 = new JLabel("List of All Questions : ");
        l4 = new JLabel("The Answer is : ");

        class ali implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                String cm = e.getActionCommand();
                if (cm == "FINDNUM") {
                    up = Qcontent.getText();
                    if (up.isEmpty() || up == null || up == "Enter Question") {
                        return;
                    }
                    if (an == "" || an == null) {
                        return;
                    }
                    for (ut = 0; ut < q.length; ut++) {
                        if (q[ut] == "" || q[ut] == null) {
                            if (an == "True") {
                                if (ut % 2 != 0) {
                                    ut = ut + 1;
                                }
                            } else if (an == "False") {
                                if (ut % 2 == 0) {
                                    ut = ut + 1;
                                }
                            }
                            Qnum.setText(Integer.toString(ut));
                            return;
                        }
                    }
                    if (an == "True") {
                        if (ut % 2 != 0) {
                            ut = ut + 1;
                        }
                    } else if (an == "False") {
                        if (ut % 2 == 0) {
                            ut = ut + 1;
                        }
                    }
                    Qnum.setText(Integer.toString(ut));
                } else if (cm == "INSERT") {
                    if (an == "") {
                        return;
                    } else if (ut == 0) {
                        return;
                    } else {
                        q1 = Arrays.copyOf(q, ut + 1);
                        q1[ut] = up;
                        q = Arrays.copyOf(q1, ut + 1);
                        q = getQlists();
                    }
                    if (ut % 2 == 0) {
                        allq = allq + ut + " :\n" + q[ut] + "\n\nAnswer is [True]\n\n";
                    } else {
                        allq = allq + ut + " :\n" + q[ut] + "\n\nAnswer is [False]\n\n";
                    }
                    QList.setText(allq);
                    Qnum.setText("");
                    Qcontent.setText("");
                    ut = 0;
                    up = "";
                } else if (cm == "CLEAR") {
                    Qcontent.setText("");
                    Qnum.setText("");
                    ut = 0;
                    an = "";
                } else if (cm == "PICKEDt") {
                    an = "True";
                    l4.setText("The Answer is : True");
                } else if (cm == "PICKEDf") {
                    an = "False";
                    l4.setText("The Answer is : False");
                }
            }
        }
        ali ALI = new ali();

        Qnum = new JTextField("Please Press the button", 15);
        Qnum.setEditable(false);
        Qcontent = new JTextField("Enter Question", 15);
        Qcontent.addActionListener(ALI);

        QList = new JTextArea(allq, 10, 12);
        QList.setEditable(false);
        QList.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));
        scl = new JScrollPane(QList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        at = new JButton("True");
        af = new JButton("False");
        at.setActionCommand("PICKEDt");
        at.addActionListener(ALI);
        af.setActionCommand("PICKEDf");
        af.addActionListener(ALI);

        ins = new JButton("Insert it");
        ins.setActionCommand("INSERT");
        ins.addActionListener(ALI);

        clear = new JButton("Clear typed question");
        clear.setActionCommand("CLEAR");
        clear.addActionListener(ALI);

        find = new JButton("Find unused number");
        find.setActionCommand("FINDNUM");
        find.addActionListener(ALI);

        showinsertq.add(l2);
        showinsertq.add(Qcontent);
        showinsertq.add(l4);
        btns2.add(at);
        btns2.add(af);
        showinsertq.add(btns2);
        showinsertq.add(l1);
        fnum.add(Qnum);
        fnum.add(find);
        showinsertq.add(fnum);
        btns.add(ins);
        btns.add(clear);
        showinsertq.add(l3);
        showallq.add(scl);
        inserting.add(showinsertq);
        inserting.add(btns);
        allinterface.add(inserting);
        allinterface.add(showallq);
        return allinterface;
    }

    public String[] getQlists() {
        this.q = q1;
        return this.q;
    }
}
