using System.Windows;

namespace SakilaNet.Views;

public partial class SplashScreen : Window
{
    public SplashScreen()
    {
        InitializeComponent();
        Loaded += SplashScreen_Loaded;
    }

    private async void SplashScreen_Loaded(object sender, RoutedEventArgs e)
    {
        await Task.Delay(2000); // Simula carga
        var login = new LoginForm();
        login.Show();
        Close();
    }
}

