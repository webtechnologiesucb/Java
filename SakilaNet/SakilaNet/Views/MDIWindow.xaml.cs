using System.Windows;

namespace SakilaNet.Views;

public partial class MDIWindow : Window
{
    public MDIWindow()
    {
        InitializeComponent();
    }
    
    private void OpenActors(object sender, RoutedEventArgs e)
    {
        MainContent.Content = new FrmActors();
    }

}