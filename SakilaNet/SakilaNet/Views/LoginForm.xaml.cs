using System.Windows;
using MySql.Data.MySqlClient;
using SakilaNet.Data;

namespace SakilaNet.Views;

public partial class LoginForm : Window
{
    public LoginForm()
    {
        InitializeComponent();
    }

    private void Login_Click(object sender, RoutedEventArgs e)
    {
        string username = txtUsername.Text.Trim();
        string password = txtPassword.Password;

        try
        {
            var db = DatabaseConnection.GetInstance();
            using var conn = db.GetConnection(); // Obtener nueva conexión
            conn.Open();

            using var cmd = new MySqlCommand("SELECT password FROM staff WHERE username = @username", conn);
            cmd.Parameters.AddWithValue("@username", username);

            using var reader = cmd.ExecuteReader();

            if (reader.Read())
            {
                string hashedPassword = reader.GetString(0);
                if (BCrypt.Net.BCrypt.Verify(password, hashedPassword))
                {
                    MessageBox.Show("Login exitoso");

                    // Mostrar ventana principal
                    var mdi = new MDIWindow();
                    mdi.Show();

                    // Cerrar ventana de login
                    this.Close();
                }
                else
                {
                    MessageBox.Show("Contraseña incorrecta");
                }
            }
            else
            {
                MessageBox.Show("Usuario no encontrado");
            }
        }
        catch (Exception ex)
        {
            MessageBox.Show($"Error al iniciar sesión: {ex.Message}");
        }
    }

}