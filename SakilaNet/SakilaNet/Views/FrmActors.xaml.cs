using System.Windows;
using System.Windows.Controls;
using MySql.Data.MySqlClient;
using SakilaNet.Data;
using SakilaNet.Dialogs;
using SakilaNet.Models;

namespace SakilaNet.Views;

public partial class FrmActors : UserControl
{
    public FrmActors()
    {
        InitializeComponent();
        LoadActors();
    }

    private void LoadActors()
    {
        try
        {
            var db = DatabaseConnection.GetInstance();
            using var conn = db.GetConnection();
            conn.Open();

            using var cmd = new MySqlCommand("SELECT actor_id, first_name, last_name, last_update FROM actor", conn);
            using var reader = cmd.ExecuteReader();

            var actors = new List<Actor>();
            while (reader.Read())
            {
                actors.Add(new Actor
                {
                    ActorId = reader.GetInt32(0),
                    FirstName = reader.GetString(1),
                    LastName = reader.GetString(2),
                    LastUpdate = reader.GetDateTime(3)
                });
            }

            dgActors.ItemsSource = actors;
        }
        catch (Exception ex)
        {
            MessageBox.Show($"Error al cargar actores: {ex.Message}");
        }
    }

    private void AddActor(object sender, RoutedEventArgs e)
    {
        var dialog = new InputDialog();
        if (dialog.ShowDialog() == true)
        {
            try
            {
                var db = DatabaseConnection.GetInstance();
                using var conn = db.GetConnection();
                conn.Open();

                using var cmd = new MySqlCommand("INSERT INTO actor (first_name, last_name) VALUES (@first, @last)", conn);
                cmd.Parameters.AddWithValue("@first", dialog.FirstName);
                cmd.Parameters.AddWithValue("@last", dialog.LastName);
                cmd.ExecuteNonQuery();

                LoadActors();
            }
            catch (Exception ex)
            {
                MessageBox.Show($"Error al agregar actor: {ex.Message}");
            }
        }
    }

    private void EditActor(object sender, RoutedEventArgs e)
    {
        if (dgActors.SelectedItem is Actor selected)
        {
            var dialog = new InputDialog(selected.FirstName, selected.LastName);
            if (dialog.ShowDialog() == true)
            {
                try
                {
                    var db = DatabaseConnection.GetInstance();
                    using var conn = db.GetConnection();
                    conn.Open();

                    using var cmd = new MySqlCommand("UPDATE actor SET first_name = @first, last_name = @last WHERE actor_id = @id", conn);
                    cmd.Parameters.AddWithValue("@first", dialog.FirstName);
                    cmd.Parameters.AddWithValue("@last", dialog.LastName);
                    cmd.Parameters.AddWithValue("@id", selected.ActorId);
                    cmd.ExecuteNonQuery();

                    LoadActors();
                }
                catch (Exception ex)
                {
                    MessageBox.Show($"Error al editar actor: {ex.Message}");
                }
            }
        }
    }

    private void DeleteActor(object sender, RoutedEventArgs e)
    {
        if (dgActors.SelectedItem is Actor selected)
        {
            var result = MessageBox.Show("¿Está seguro de eliminar al actor seleccionado?", "Confirmar", MessageBoxButton.YesNo);
            if (result == MessageBoxResult.Yes)
            {
                try
                {
                    var db = DatabaseConnection.GetInstance();
                    using var conn = db.GetConnection();
                    conn.Open();

                    using var cmd = new MySqlCommand("DELETE FROM actor WHERE actor_id = @id", conn);
                    cmd.Parameters.AddWithValue("@id", selected.ActorId);
                    cmd.ExecuteNonQuery();

                    LoadActors();
                }
                catch (Exception ex)
                {
                    MessageBox.Show($"Error al eliminar actor: {ex.Message}");
                }
            }
        }
    }
}
