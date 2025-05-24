using MySql.Data.MySqlClient;

namespace SakilaNet.Data
{
    public class DatabaseConnection
    {
        private static DatabaseConnection _instance;
        private static readonly object _lock = new();

        private readonly string _connectionString = "server=localhost;user=root;password=;database=sakila";

        // Constructor privado
        private DatabaseConnection() { }

        // Singleton: instancia única de la clase DatabaseConnection
        public static DatabaseConnection GetInstance()
        {
            if (_instance == null)
            {
                lock (_lock)
                {
                    if (_instance == null)
                        _instance = new DatabaseConnection();
                }
            }
            return _instance;
        }

        // Método que retorna una nueva conexión cada vez
        public MySqlConnection GetConnection()
        {
            return new MySqlConnection(_connectionString);
        }
    }
}
