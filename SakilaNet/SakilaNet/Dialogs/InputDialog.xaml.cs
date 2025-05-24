using System.Windows;

namespace SakilaNet.Dialogs;

public partial class InputDialog : Window
{
    public string FirstName => txtFirstName.Text;
    public string LastName => txtLastName.Text;

    public InputDialog(string firstName = "", string lastName = "")
    {
        InitializeComponent();
        txtFirstName.Text = firstName;
        txtLastName.Text = lastName;
    }

    private void Accept(object sender, RoutedEventArgs e)
    {
        DialogResult = true;
    }
}