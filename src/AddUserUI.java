import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Calendar;

public class AddUserUI {
    public JFrame view;

    public JButton btnAdd = new JButton("Add");
    public JButton btnCancel = new JButton("Cancel");

    public JTextField txtUsername = new JTextField(10);
    public JTextField txtPassword = new JTextField(10);
    public JTextField txtFullname = new JTextField(10);
    public JTextField txtUserType = new JTextField(10);

    UserModel user;

    public AddUserUI() {
        this.view = new JFrame();

        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        view.setTitle("Add Purchase");
        view.setSize(600, 400);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        JPanel line = new JPanel(new FlowLayout());
        line.add(new JLabel("Username "));
        line.add(txtUsername);
        view.getContentPane().add(line);

        line = new JPanel(new FlowLayout());
        line.add(new JLabel("Password "));
        line.add(txtPassword);
        view.getContentPane().add(line);

        line = new JPanel(new FlowLayout());
        line.add(new JLabel("Full name "));
        line.add(txtFullname);
        view.getContentPane().add(line);

        line = new JPanel(new FlowLayout());
        line.add(new JLabel("User Type "));
        line.add(txtUserType);
        view.getContentPane().add(line);

        line = new JPanel(new FlowLayout());
        line.add(btnAdd);
        line.add(btnCancel);
        view.getContentPane().add(line);


        btnAdd.addActionListener(new AddButtonListerner());
    }

    public void run() {
        user = new UserModel();
        view.setVisible(true);
    }


    class AddButtonListerner implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            String id = txtUsername.getText();
            String pw = txtPassword.getText();
            String name = txtFullname.getText();
            String usertype = txtUserType.getText();

            if (id.length() == 0) {
                JOptionPane.showMessageDialog(null, "Username cannot be null!");
                return;
            }
            try {
                user.mUsername = id;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Username is invalid!");
                return;
            }

            if (pw.length() == 0) {
                JOptionPane.showMessageDialog(null, "Password cannot be null!");
                return;
            }
            try {
                user.mPassword = pw;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Password is invalid!");
                return;
            }

            if (name.length() == 0) {
                JOptionPane.showMessageDialog(null, "Full name cannot be null!");
                return;
            }
            try {
                user.mFullname = name;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Full name is invalid!");
                return;
            }

            if (usertype.length() == 0) {
                JOptionPane.showMessageDialog(null, "User type cannot be null!");
                return;
            }
            try {
                user.mUserType = Integer.parseInt(usertype);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "User type is invalid!");
                return;
            }

            int res = StoreManager.getInstance().getDataAdapter().saveUser(user);
            if (res == SQLiteDataAdapter.USER_SAVE_FAILED)
                JOptionPane.showMessageDialog(null, "User NOT added successfully!");
            else
                JOptionPane.showMessageDialog(null, "User added successfully!" + user);
        }
    }

}
