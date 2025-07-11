using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Runtime.InteropServices;
using System.IO;
using System.Text.RegularExpressions;
using System.Globalization;
using Excel = Microsoft.Office.Interop.Excel;

namespace Calculadora_140BISV2
{
    public partial class Form1 : Form
    {
        // las siguientes 3 es para buscar por una frase que contenga la ventana
        [DllImport("user32.dll", CharSet = CharSet.Auto, SetLastError = true)]
        private static extern bool EnumWindows(EnumWindowsProc lpEmunFunc, IntPtr lParam);

        [DllImport("user32.dll", CharSet = CharSet.Auto)]
        private static extern int GetWindowText(IntPtr hWnd, StringBuilder lpString, int nMaxCount);

        [DllImport("user32.dll", CharSet = CharSet.Auto)]
        private static extern bool IsWindowVisible(IntPtr hWnd);

        private delegate bool EnumWindowsProc(IntPtr hWnd, IntPtr lParam);

        //Traerla si esta minimizada
        [DllImport("user32.dll")]
        private static extern bool ShowWindow(IntPtr hWnd, int nCmdShow);
        private const int SW_RESTORE = 9; //Restaura ventana minimizada
        private const int SW_SHOW = 5; //Mostrar ventana si esta oculta

        //Definir las funciones de la API de Windows necesarias para traer una ventana al frente
        [DllImport("user32.dll", CharSet = CharSet.Auto)]
        public static extern IntPtr FindWindow(string lpClassName, string lpWindowName);

        [DllImport("user32.dll")]
        [return: MarshalAs(UnmanagedType.Bool)]
        //public static extern bool SetForegroundWindow(IntPtr hWnd);

        //Para verificar si esta activa la ventana
        //[DllImport("user32.dll", CharSet = CharSet.Auto)]
        private static extern bool IsWindow(IntPtr hWnd);

        [DllImport("user32.dll")]
        [return: MarshalAs(UnmanagedType.Bool)]
        public static extern bool SetForegroundWindow(IntPtr hWnd);
        //Para simular el boton CE que es el 5 del teclado numerico
        [DllImport("user32.dll", EntryPoint = "keybd_event")]
        private static extern void keybd_event(byte bVk, byte bScan, int dwFlags, int dwExtraInfo);
        private const byte VK_NUMLOCK = 0x90;
        private const int KEYEVENTF_EXTENDEDKEY = 0x01;
        private const int KEYEVENTF_KEYUP = 0x02;

        public static string USUARIO, CONTRASEÑA, REGION_FORMS, NSS, NSSCOMPLETO, NOMBRE_ASEGURADO, CURP, SALARIO;
        public static string Ruta_Carpeta = null, Ruta_Carpeta_Excel = null;
        public static string Ruta_Guardar_NSS = null, Ruta_Final_NSS, Ruta_NSS_Limpiado, Ruta_Guardar_Excel;
        public static string RutaInfoCol1, RutaInfoCol2, RutaInfoCol3, RutaInfoCol4, RutaInfoCol5, RutaInfoCol6, RutaInfoCol7, RutaInfoCol8;
        public static string Salariotxt, Salariotxt1Global, Salariotxt2Global, Sem30_1;
        public static string ExtensionTXT = ".txt", ExtensionEXCEL = ".xlsx";
        public static string Ventana, TituloClave = "(idc2.imss.gob.mx)";
        public static string ArchivoConfiguracionRuta = "Configuracion_Ruta_TXTs.txt";
        public static string ArchivoConfiguracionRutaExcel = "Configuracion_Ruta_Excels.txt";
        public static int ValidadorDeDerecho = 0;
        public static string Texto_Fecha_Solicitud, Texto_Fecha_Inicio_Licencia, Texto_Fecha_Diagnostico;
        public static int Autorizador_Conexion = 0, Region_Obtenida = 5, Recorredor_Regiones = 1;
        public static int Autorizador_Conexion_Usuario = 0, Autorizador_Conexion_Menu = 0;
        public static double SalarioFininalObtenido = 0;

        public static DateTime Fecha_Solicitud, Fecha_Inicio_Licencia, Fecha_Diagnostico;

        public class CustomRenderer : ToolStripProfessionalRenderer
        {
            protected override void OnRenderMenuItemBackground(ToolStripItemRenderEventArgs e)
            {
                if (e.Item.Selected)
                {
                    e.Graphics.FillRectangle(Brushes.LightGray, e.Item.ContentRectangle);
                    e.Graphics.DrawRectangle(Pens.Black, e.Item.ContentRectangle);
                }
                else
                {
                    e.Graphics.FillRectangle(Brushes.LightGray, e.Item.ContentRectangle);
                }
            }
        }

        public Form1()
        {
            InitializeComponent();
        }

        private static void CargarRutaCarpeta()
        {
            try
            {
                if (File.Exists(ArchivoConfiguracionRuta) && File.Exists(ArchivoConfiguracionRutaExcel))
                {
                    Ruta_Carpeta = File.ReadAllText(ArchivoConfiguracionRuta);
                    Ruta_Carpeta_Excel = File.ReadAllText(ArchivoConfiguracionRutaExcel);
                }
                else
                {
                    SeleccionarCarpeta();
                }
            }
            catch (Exception ex)
            {
                IntPtr hWnd3 = FindWindow(null, "Calculadora 140Bis");
                SetForegroundWindow(hWnd3);
                MessageBox.Show("Error: " + ex.Message, "Calculadora 140Bis");
            }
        }

        private static void SeleccionarCarpeta()
        {
            try
            {
                using (FolderBrowserDialog dialogo = new FolderBrowserDialog())
                {
                    if (dialogo.ShowDialog() == DialogResult.OK)
                    {
                        string RutaSeleccionada1 = dialogo.SelectedPath;
                        File.WriteAllText(ArchivoConfiguracionRuta, RutaSeleccionada1);
                        File.WriteAllText(ArchivoConfiguracionRutaExcel, RutaSeleccionada1);
                    }
                }
            }
            catch (Exception ex)
            {
                IntPtr hWnd3 = FindWindow(null, "Calculadora 140Bis");
                SetForegroundWindow(hWnd3);
                MessageBox.Show("Error: " + ex.Message, "Calculadora 140Bis");
            }
        }

        private void button_INICIO_PROGRAMA_Click(object sender, EventArgs e)
        {
            try
            {
                CargarRutaCarpeta();
                Recorredor_Regiones = 1;

                if (File.Exists(Ruta_Guardar_NSS)) { File.Delete(Ruta_Guardar_NSS); }
                if (File.Exists(Ruta_NSS_Limpiado)) { File.Delete(Ruta_NSS_Limpiado); }
                if (File.Exists(Ruta_Final_NSS)) { File.Delete(Ruta_Final_NSS); }
                if (File.Exists(RutaInfoCol1)) { File.Delete(RutaInfoCol1); }
                if (File.Exists(RutaInfoCol2)) { File.Delete(RutaInfoCol2); }
                if (File.Exists(RutaInfoCol3)) { File.Delete(RutaInfoCol3); }
                if (File.Exists(RutaInfoCol4)) { File.Delete(RutaInfoCol4); }
                if (File.Exists(RutaInfoCol5)) { File.Delete(RutaInfoCol5); }
                if (File.Exists(RutaInfoCol6)) { File.Delete(RutaInfoCol6); }
                if (File.Exists(RutaInfoCol7)) { File.Delete(RutaInfoCol7); }
                if (File.Exists(RutaInfoCol8)) { File.Delete(RutaInfoCol8); }
                if (File.Exists(Sem30_1)) { File.Delete(Sem30_1); }
                if (File.Exists(Salariotxt1Global)) { File.Delete(Salariotxt1Global); }
                if (File.Exists(Salariotxt2Global)) { File.Delete(Salariotxt2Global); }

                USUARIO = textBox_USUARIO_SINDO.Text;
                CONTRASEÑA = textBox_CONTRASEÑA_SINDO.Text;
                NSS = textBox_NSS.Text;
                NSSCOMPLETO = textBox_NSS.Text;
                Texto_Fecha_Diagnostico = textBox_FECHA_DIAGNOSTICO.Text.Trim();
                Texto_Fecha_Inicio_Licencia = textBox_FECHA_INICIO_LICENCIA.Text.Trim();
                Texto_Fecha_Solicitud = textBox_FECHA_SOLICITUD.Text.Trim();
                DateTime.TryParseExact(Texto_Fecha_Diagnostico, "dd/MM/yyyy", CultureInfo.InvariantCulture, DateTimeStyles.None, out Fecha_Diagnostico);
                DateTime.TryParseExact(Texto_Fecha_Inicio_Licencia, "dd/MM/yyyy", CultureInfo.InvariantCulture, DateTimeStyles.None, out Fecha_Inicio_Licencia);
                DateTime.TryParseExact(Texto_Fecha_Solicitud, "dd/MM/yyyy", CultureInfo.InvariantCulture, DateTimeStyles.None, out Fecha_Solicitud);

                Ruta_Guardar_NSS = Path.Combine(Ruta_Carpeta, NSS + ExtensionTXT);
                string NombreArchivo = NSS + "Limpiado";
                Ruta_NSS_Limpiado = Path.Combine(Ruta_Carpeta, NombreArchivo + ExtensionTXT);
                string NombreArchivo1 = NSS + "Ordenado";
                Ruta_Final_NSS = Path.Combine(Ruta_Carpeta, NombreArchivo1 + ExtensionTXT);
                string NombreArchivo2 = "Salario1";
                Salariotxt = Path.Combine(Ruta_Carpeta, NombreArchivo2 + ExtensionTXT);
                string NombreArchivo3 = "Info_1columnas";
                RutaInfoCol1 = Path.Combine(Ruta_Carpeta, NombreArchivo3 + ExtensionTXT);
                string NombreArchivo4 = "Info_2Concatenado";
                RutaInfoCol2 = Path.Combine(Ruta_Carpeta, NombreArchivo4 + ExtensionTXT);
                string NombreArchivo5 = "Info_3Traslapado";
                RutaInfoCol3 = Path.Combine(Ruta_Carpeta, NombreArchivo5 + ExtensionTXT);
                string NombreArchivo6 = "Info_4Final";
                RutaInfoCol4 = Path.Combine(Ruta_Carpeta, NombreArchivo6 + ExtensionTXT);
                string NombreArchivo7 = "5Final_30S";
                RutaInfoCol5 = Path.Combine(Ruta_Carpeta, NombreArchivo7 + ExtensionTXT);
                string NombreArchivo8 = "30Semanas";
                RutaInfoCol6 = Path.Combine(Ruta_Carpeta, NombreArchivo8 + ExtensionTXT);
                string NombreArchivo9 = "6Final_52S";
                RutaInfoCol7 = Path.Combine(Ruta_Carpeta, NombreArchivo9 + ExtensionTXT);
                string NombreArchivo10 = "52Semanas";
                RutaInfoCol8 = Path.Combine(Ruta_Carpeta, NombreArchivo10 + ExtensionTXT);
                Ruta_Guardar_Excel = Path.Combine(Ruta_Carpeta_Excel, NSS + ExtensionEXCEL);

                string NombreArchivo11 = "30Semanas";
                Sem30_1 = Path.Combine(Ruta_Carpeta, NombreArchivo11 + ExtensionTXT);
                string NombreArchivo12 = "5Final_30S";
                string Final_30S = Path.Combine(Ruta_Carpeta, NombreArchivo12 + ExtensionTXT);
                string NombreArchivo13 = "Salario1";
                Salariotxt1Global = Path.Combine(Ruta_Carpeta, NombreArchivo13 + ExtensionTXT);
                string NombreArchivo14 = "Salario";
                Salariotxt2Global = Path.Combine(Ruta_Carpeta, NombreArchivo14 + ExtensionTXT);
                string NombreArchivo15 = "52Semanas";
                string Semanas52 = Path.Combine(Ruta_Carpeta, NombreArchivo15 + ExtensionTXT);
                string NombreArchivo16 = "6Final_52S";
                string FinalSemans52 = Path.Combine(Ruta_Carpeta, NombreArchivo16 + ExtensionTXT);

                if (!string.IsNullOrEmpty(USUARIO) && !string.IsNullOrEmpty(CONTRASEÑA) && !string.IsNullOrEmpty(NSS)
                    && !string.IsNullOrEmpty(Texto_Fecha_Diagnostico) && !string.IsNullOrEmpty(Texto_Fecha_Inicio_Licencia)
                    && !string.IsNullOrEmpty(Texto_Fecha_Solicitud))
                {
                    if (File.Exists(Ruta_Guardar_NSS)){ File.Delete(Ruta_Guardar_NSS); }
                    if (File.Exists(Ruta_NSS_Limpiado)){ File.Delete(Ruta_NSS_Limpiado); }
                    if (File.Exists(Ruta_Final_NSS)){ File.Delete(Ruta_Final_NSS); }
                    if (File.Exists(RutaInfoCol1)){ File.Delete(RutaInfoCol1); }
                    if (File.Exists(RutaInfoCol2)){ File.Delete(RutaInfoCol2); }
                    if (File.Exists(RutaInfoCol3)){ File.Delete(RutaInfoCol3); }
                    if (File.Exists(RutaInfoCol4)){ File.Delete(RutaInfoCol4); }
                    if (File.Exists(RutaInfoCol5)){ File.Delete(RutaInfoCol5); }
                    if (File.Exists(RutaInfoCol6)){ File.Delete(RutaInfoCol6); }
                    if (File.Exists(RutaInfoCol7)){ File.Delete(RutaInfoCol7); }
                    if (File.Exists(RutaInfoCol8)){ File.Delete(RutaInfoCol8); }
                    if (File.Exists(Sem30_1)) { File.Delete(Sem30_1); }
                    if (File.Exists(Salariotxt1Global)) { File.Delete(Salariotxt1Global); }
                    if (File.Exists(Salariotxt2Global)) { File.Delete(Salariotxt2Global); }

                    Activador_SINDO();
                    //Limpiar();
                    if (Recorredor_Regiones == 4)
                    {
                        label_Fecha_Diagnostico.Text = "FECHA DIAGNOSTICO: " + Fecha_Diagnostico.ToString("dd/MM/yyyy");
                        label_Fecha_Inicio_Licencia.Text = "FECHA INICIO LICENCIA: " + Fecha_Inicio_Licencia.ToString("dd/MM/yyyy");
                        label_Fecha_Solicitud.Text = "FECHA SOLICITUD: " + Fecha_Solicitud.ToString("dd/MM/yyyy");
                        //label_Salario.Text = "SALARIO" + SALARIO;
                        label_NSS.Text = "NSS: " + NSSCOMPLETO;

                        label_Nombre.Text = "Nombre Asegurado: " + NOMBRE_ASEGURADO;
                        label_CURP.Text = "CURP: " + CURP;

                        using (StreamReader reader = new StreamReader(Sem30_1))
                        {
                            label_Semanas_Validas_30S.Text = "SEMANAS TOTALES: " + reader.ReadLine();
                            reader.Close();
                        }
                        //AQUI MOSTRAMOS LO DE LAS 30 SEMANAS
                        var lineas = File.ReadAllLines(Final_30S); //AQUI ESTA LEYENDO CUANTAS LINEAS TIENE

                        if (lineas.Length == 0) return;
                        int numFilas = lineas.Length;
                        int numColumnas = lineas[0].Split(',').Length;
                        // AQUI LO MOSTRAMOS EN UNA TABLA QUE ESTA EN DESUSO.
                        tableLayoutPanel1.RowCount = numFilas;
                        tableLayoutPanel1.ColumnCount = numColumnas;
                        tableLayoutPanel1.Controls.Clear(); //Limpia la tabla
                        tableLayoutPanel1.ColumnStyles.Clear();
                        tableLayoutPanel1.RowStyles.Clear();

                        for (int i = 0; i < numColumnas; i++)
                        {
                            tableLayoutPanel1.ColumnStyles.Add(new ColumnStyle(SizeType.Percent, 100f / numColumnas));
                        }
                        for (int i = 0; i < numFilas; i++)
                        {
                            tableLayoutPanel1.RowStyles.Add(new RowStyle(SizeType.AutoSize));
                        }
                        //AÑADE LAS CELADAS
                        for (int fila = 0; fila < numFilas; fila++)
                        {
                            string[] columnas = lineas[fila].Split(',');
                            if (columnas.Length < numColumnas) //VERIFICA QUE TODAS LAS FILAS TENGAN EL MISMO NUMERO DE COLUMAS.
                            {
                                Array.Resize(ref columnas, numColumnas);
                            }
                            for (int col = 0; col < numColumnas; col++)
                            {
                                Label labelT = new Label
                                {
                                    Text = (columnas[col] != null ? columnas[col].Trim() : ""),
                                    AutoSize = true
                                };
                                tableLayoutPanel1.Controls.Add(labelT, col, fila);
                            }
                        }
                        if (ValidadorDeDerecho == 1)
                        {
                            label_Derecho.Text = "Tiene Derecho: SI";
                        }
                        else
                        {
                            label_Derecho.Text = "Tiene Derecho: NO";
                        }
                        string lin; SalarioFininalObtenido = 0;
                        
                        using (StreamReader readers = new StreamReader(Salariotxt1Global))
                        {
                            while ((lin = readers.ReadLine()) != null)
                            {
                                double salario = double.Parse(lin);

                                SalarioFininalObtenido = SalarioFininalObtenido + salario;
                            }
                        } //Agregado 19/12*24

                        if (SalarioFininalObtenido > 2593)
                        {
                            label_Salario.Text = "Salario: $2593 Pesos (Tope Salarial)";
                        }
                        else
                        {
                            label_Salario.Text = "Salario: $" + SalarioFininalObtenido.ToString() + " Pesos";
                        }

                        using (StreamWriter writer = new StreamWriter(Salariotxt2Global))
                        {
                            writer.WriteLine(SalarioFininalObtenido);
                        }

                        //AQUI MOSTRAMOS LO DE LAS 52 SEMANAS

                        if (File.Exists(Semanas52))
                        {
                            using (StreamReader reader2 = new StreamReader(Semanas52))
                            {
                                label_Semanas_Validas_52S.Text = "SEMANAS TOTALES: " + reader2.ReadLine();
                                reader2.Close();
                            }
                        }
                        else
                        {
                            label_Semanas_Validas_52S.Text = " ";
                        }
                        if (File.Exists(FinalSemans52))
                        {
                            var lineas2 = File.ReadAllLines(FinalSemans52); //AQUI ESTA LEYENDO CUANTAS LINEAS TIENE
                            if (lineas2.Length == 0) return;
                            int numFilas2 = lineas2.Length;
                            int numColumnas2 = lineas2[0].Split(',').Length;
                            // AQUI LO MOSTRAMOS EN UNA TABLA QUE ESTA EN DESUSO.
                            tableLayoutPanel2.RowCount = numFilas2;
                            tableLayoutPanel2.ColumnCount = numColumnas2;
                            tableLayoutPanel2.Controls.Clear(); //Limpia la tabla
                            tableLayoutPanel2.ColumnStyles.Clear();
                            tableLayoutPanel2.RowStyles.Clear();

                            for (int i = 0; i < numColumnas2; i++)
                            {
                                tableLayoutPanel2.ColumnStyles.Add(new ColumnStyle(SizeType.Percent, 100f / numColumnas2));
                            }
                            for (int i = 0; i < numFilas; i++)
                            {
                                tableLayoutPanel2.RowStyles.Add(new RowStyle(SizeType.AutoSize));
                            }
                            //AÑADE LAS CELADAS
                            for (int fila2 = 0; fila2 < numFilas2; fila2++)
                            {
                                string[] columnas2 = lineas2[fila2].Split(',');
                                if (columnas2.Length < numColumnas2) //VERIFICA QUE TODAS LAS FILAS TENGAN EL MISMO NUMERO DE COLUMAS.
                                {
                                    Array.Resize(ref columnas2, numColumnas2);
                                }
                                for (int col2 = 0; col2 < numColumnas2; col2++)
                                {
                                    Label labelT2 = new Label
                                    {
                                        Text = (columnas2[col2] != null ? columnas2[col2].Trim() : ""),
                                        AutoSize = true
                                    };
                                    tableLayoutPanel2.Controls.Add(labelT2, col2, fila2);
                                }
                            }
                        }
                        else
                        {
                            label3.Text = " ";
                            tableLayoutPanel2.Controls.Clear();
                            tableLayoutPanel2.RowCount = 0;
                            tableLayoutPanel2.ColumnCount = 0;
                        }
                        tabControl1.SelectedIndex = 1;

                        IntPtr hWnd3 = FindWindow(null, "Calculadora 140Bis");
                        SetForegroundWindow(hWnd3);
                        MessageBox.Show("El programa ha finalizado", "Calculadora 140Bis");
                    }
                }
                else
                {
                    MessageBox.Show("Llene los campos solicitados", "Ingreso");
                }
            }
            catch (Exception ex)
            {
                IntPtr hWnd3 = FindWindow(null, "Calculadora 140Bis");
                SetForegroundWindow(hWnd3);
                MessageBox.Show("Error: " + ex.Message, "Calculadora 140Bis");
            }
        }

        private static void Conectar_Desconectar()
        {
            try
            {
                SendKeys.SendWait("%A");
                System.Threading.Thread.Sleep(1000);
                //teclas S desconecta sindo
                SendKeys.SendWait("S");
                System.Threading.Thread.Sleep(1000);
                //Tecla T conecta SINDO
                SendKeys.SendWait("%A");
                System.Threading.Thread.Sleep(1000);
                SendKeys.SendWait("T");
                System.Threading.Thread.Sleep(1000);
            }
            catch (Exception ex)
            {
                IntPtr hWnd3 = FindWindow(null, "Calculadora 140Bis");
                SetForegroundWindow(hWnd3);
                MessageBox.Show("Error: " + ex.Message, "Calculadora 140Bis");
            }
        }

        public static void Verificador_Sindo_Activo()
        {
            try
            {
                bool BloqNum = Control.IsKeyLocked(Keys.NumLock);
                if (BloqNum)
                {   //activado
                    keybd_event(VK_NUMLOCK, 0x45, KEYEVENTF_EXTENDEDKEY, 0);
                    keybd_event(VK_NUMLOCK, 0x45, KEYEVENTF_EXTENDEDKEY | KEYEVENTF_KEYUP, 0);

                    SendKeys.Send("^{CLEAR}"); //simular el ctrl + CLEAR que seleecionatodo
                }
                else
                {   //desactivado
                    SendKeys.Send("^{CLEAR}"); //simular el ctrl + CLEAR que seleecionatodo
                }
                System.Windows.Forms.Clipboard.Clear();
                System.Threading.Thread.Sleep(1500);
                SendKeys.SendWait("^c");
                System.Threading.Thread.Sleep(1500);
                string Verificacion_Conexion = System.Windows.Forms.Clipboard.GetText();

                if (!string.IsNullOrWhiteSpace(Verificacion_Conexion))
                {
                    string[] FilasVerificacionConexion = Verificacion_Conexion.Split(new[] { Environment.NewLine }, StringSplitOptions.RemoveEmptyEntries);
                    if (FilasVerificacionConexion.Length > 0)
                    {
                        if (Verificacion_Conexion == "Su conexión se ha dado por finalizada" ||
                            Verificacion_Conexion == "\r\n\r\nSu conexión se ha dado por finalizada\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n")
                        {
                            Autorizador_Conexion = 0;
                        }
                        else
                        {
                            string UltimaFilaVerConexcion = FilasVerificacionConexion[FilasVerificacionConexion.Length - 1];
                            string UltimaFilaVerConexcion2 = FilasVerificacionConexion[FilasVerificacionConexion.Length - 2];

                            if (UltimaFilaVerConexcion == ", ***   **  ******* *******     ******        <ENTER> PARA ACCESAR,CICS01SS,=>," ||
                                UltimaFilaVerConexcion == ", ***   **  ******* *******    ********       <ENTER> PARA ACCESAR,CICS02SS,=>," ||
                                UltimaFilaVerConexcion == ", **    **  ******* *******   ********        <ENTER> PARA ACCESAR,CICS03SS,=>," ||
                                UltimaFilaVerConexcion2 == ", ***   **  ******* *******     ******        <ENTER> PARA ACCESAR,CICS01SS,=>," ||
                                UltimaFilaVerConexcion2 == ", ***   **  ******* *******    ********       <ENTER> PARA ACCESAR,CICS02SS,=>," ||
                                UltimaFilaVerConexcion2 == ", **    **  ******* *******   ********        <ENTER> PARA ACCESAR,CICS03SS,=>,")
                            {
                                Autorizador_Conexion = 1;
                            }
                            else
                            {
                                Autorizador_Conexion = 0;
                            }
                        }
                    }
                }
            }
            catch (Exception ex)
            {
                IntPtr hWnd3 = FindWindow(null, "Calculadora 140Bis");
                SetForegroundWindow(hWnd3);
                MessageBox.Show("Error: " + ex.Message, "Calculadora 140Bis");
            }
        }

        public static void Verificador_Sindo_Activo_Usuario()
        {
            try
            {
                bool BloqNum = Control.IsKeyLocked(Keys.NumLock);
                if (BloqNum)
                {   //activado
                    keybd_event(VK_NUMLOCK, 0x45, KEYEVENTF_EXTENDEDKEY, 0);
                    keybd_event(VK_NUMLOCK, 0x45, KEYEVENTF_EXTENDEDKEY | KEYEVENTF_KEYUP, 0);

                    SendKeys.Send("^{CLEAR}"); //simular el ctrl + CLEAR que seleecionatodo
                }
                else
                {   //desactivado
                    SendKeys.Send("^{CLEAR}"); //simular el ctrl + CLEAR que seleecionatodo
                }
                System.Windows.Forms.Clipboard.Clear();
                System.Threading.Thread.Sleep(1500);
                SendKeys.SendWait("^c");
                System.Threading.Thread.Sleep(1500);
                string Verificacion_Conexion_Usuario = System.Windows.Forms.Clipboard.GetText();

                if (!string.IsNullOrWhiteSpace(Verificacion_Conexion_Usuario))
                {
                    string[] FilasVerificacionConexionUsuario = Verificacion_Conexion_Usuario.Split(new[] { Environment.NewLine }, StringSplitOptions.RemoveEmptyEntries);
                    if (FilasVerificacionConexionUsuario.Length > 0)
                    {
                        string UltimaFilaVerConexcionUsuario = FilasVerificacionConexionUsuario[FilasVerificacionConexionUsuario.Length - 1];
                        string UltimaFilaVerConexcionUsuario2 = FilasVerificacionConexionUsuario[FilasVerificacionConexionUsuario.Length - 2];

                        if (UltimaFilaVerConexcionUsuario == ",DFHCE3523 Please type your password." ||
                            UltimaFilaVerConexcionUsuario == ",F3=Exit                                                                      ," ||
                            UltimaFilaVerConexcionUsuario2 == ",DFHCE3523 Please type your password." ||
                            UltimaFilaVerConexcionUsuario2 == ",F3=Exit                                                                      ,")
                        {
                            Autorizador_Conexion_Usuario = 1;
                        }
                        else
                        {
                            Autorizador_Conexion_Usuario = 0;
                        }
                    }
                }
            }
            catch (Exception ex)
            {
                IntPtr hWnd3 = FindWindow(null, "Calculadora 140Bis");
                SetForegroundWindow(hWnd3);
                MessageBox.Show("Error: " + ex.Message, "Calculadora 140Bis");
            }
        }

        public static void Verificador_Sindo_Activo_Menu()
        {
            try
            {
                bool BloqNum = Control.IsKeyLocked(Keys.NumLock);
                if (BloqNum)
                {   //activado
                    keybd_event(VK_NUMLOCK, 0x45, KEYEVENTF_EXTENDEDKEY, 0);
                    keybd_event(VK_NUMLOCK, 0x45, KEYEVENTF_EXTENDEDKEY | KEYEVENTF_KEYUP, 0);

                    SendKeys.Send("^{CLEAR}"); //simular el ctrl + CLEAR que seleecionatodo
                }
                else
                {   //desactivado
                    SendKeys.Send("^{CLEAR}"); //simular el ctrl + CLEAR que seleecionatodo
                }
                System.Windows.Forms.Clipboard.Clear();
                System.Threading.Thread.Sleep(1500);
                SendKeys.SendWait("^c");
                System.Threading.Thread.Sleep(1500);
                string Verificacion_Conexion_Menu = System.Windows.Forms.Clipboard.GetText();

                if (!string.IsNullOrWhiteSpace(Verificacion_Conexion_Menu))
                {
                    string[] FilasVerificacionConexionMenu = Verificacion_Conexion_Menu.Split(new[] { Environment.NewLine }, StringSplitOptions.RemoveEmptyEntries);
                    if (FilasVerificacionConexionMenu.Length > 0)
                    {
                        string UltimaFilaVerConexcionMenu = FilasVerificacionConexionMenu.First();

                        if (UltimaFilaVerConexcionMenu.StartsWith(",S.IN.D.O.  ,SZ00M            ,C  O  N  S  U  L  T  A"))
                        {
                            Autorizador_Conexion_Menu = 1;
                        }
                        else
                        {
                            Autorizador_Conexion_Menu = 0;
                        }
                    }
                }
            }
            catch (Exception ex)
            {
                IntPtr hWnd3 = FindWindow(null, "Calculadora 140Bis");
                SetForegroundWindow(hWnd3);
                MessageBox.Show("Error: " + ex.Message, "Calculadora 140Bis");
            }
        }

        public static void Activador_SINDO()
        {
            try
            {
                int Verificador_Ventana_Exista = 1;

                while (Verificador_Ventana_Exista == 1)
                {
                    //Esto es para que busque con un fragmento de titulo
                    EnumWindows((hWnd, lParam) =>
                    {
                        StringBuilder windowText = new StringBuilder(256);
                        GetWindowText(hWnd, windowText, windowText.Capacity);
                        if (IsWindowVisible(hWnd) && windowText.ToString().Contains(TituloClave))
                        {
                            Ventana = windowText.ToString();
                            ShowWindow(hWnd, SW_RESTORE); //Restaura la ventana si esta minimizada
                            SetForegroundWindow(hWnd); //Esto llama la ventana
                        }
                        return true;
                    }, IntPtr.Zero);
                    if (Ventana != null)
                    {
                        Comandos();
                        break;
                    }
                    else
                    {
                        MessageBox.Show("Programa No Encontrado, Verifique que SINDO este activo", "Extractor Informacion SINDO");
                        break;
                    }
                }
            }
            catch (Exception ex)
            {
                IntPtr hWnd3 = FindWindow(null, "Calculadora 140Bis");
                SetForegroundWindow(hWnd3);
                MessageBox.Show("Error: " + ex.Message, "Calculadora 140Bis");
            }
        }

        public static void Comandos() //AQUI MANDAMOS LOS COMANDOS
        {
            try
            {
                while (Recorredor_Regiones != 4)
                {
                    Autorizador_Conexion = 0;

                    string REGION_SINDO = "";
                    switch (Recorredor_Regiones)
                    {
                        case 1:
                            REGION_SINDO = "CI1SS";
                            break;
                        case 2:
                            REGION_SINDO = "CI2SS";
                            break;
                        case 3:
                            REGION_SINDO = "CI3SS";
                            break;
                        default:
                            break;
                    };

                    System.Threading.Thread.Sleep(1000);
                    int iIdleTime = 2000;
                    IntPtr hWnd_Ven = FindWindow(null, Ventana);
                    if (hWnd_Ven == IntPtr.Zero)
                    {
                        if (File.Exists(Ruta_Guardar_NSS)) { File.Delete(Ruta_Guardar_NSS); }
                        if (File.Exists(Ruta_NSS_Limpiado)) { File.Delete(Ruta_NSS_Limpiado); }
                        if (File.Exists(Ruta_Final_NSS)) { File.Delete(Ruta_Final_NSS); }
                        if (File.Exists(RutaInfoCol1)) { File.Delete(RutaInfoCol1); }
                        if (File.Exists(RutaInfoCol2)) { File.Delete(RutaInfoCol2); }
                        if (File.Exists(RutaInfoCol3)) { File.Delete(RutaInfoCol3); }
                        if (File.Exists(RutaInfoCol4)) { File.Delete(RutaInfoCol4); }
                        if (File.Exists(RutaInfoCol5)) { File.Delete(RutaInfoCol5); }
                        if (File.Exists(RutaInfoCol6)) { File.Delete(RutaInfoCol6); }
                        if (File.Exists(RutaInfoCol7)) { File.Delete(RutaInfoCol7); }
                        if (File.Exists(RutaInfoCol8)) { File.Delete(RutaInfoCol8); }
                        if (File.Exists(Sem30_1)) { File.Delete(Sem30_1); }
                        if (File.Exists(Salariotxt1Global)) { File.Delete(Salariotxt1Global); }
                        if (File.Exists(Salariotxt2Global)) { File.Delete(Salariotxt2Global); }
                        Recorredor_Regiones = 1;
                        return;
                    }
                    else
                    {
                        SendKeys.Send(REGION_SINDO); //Enviar el comando usando el objeto currentHost
                        SendKeys.Send("{ENTER}"); // Ejecuta "intro"
                    }

                    //AQUI PONDRE UNA VERIFICACION DE SI SE CONECTO O NO
                    while (Autorizador_Conexion != 1)
                    {
                        hWnd_Ven = FindWindow(null, Ventana);
                        if (hWnd_Ven == IntPtr.Zero)
                        {
                            if (File.Exists(Ruta_Guardar_NSS)) { File.Delete(Ruta_Guardar_NSS); }
                            if (File.Exists(Ruta_NSS_Limpiado)) { File.Delete(Ruta_NSS_Limpiado); }
                            if (File.Exists(Ruta_Final_NSS)) { File.Delete(Ruta_Final_NSS); }
                            if (File.Exists(RutaInfoCol1)) { File.Delete(RutaInfoCol1); }
                            if (File.Exists(RutaInfoCol2)) { File.Delete(RutaInfoCol2); }
                            if (File.Exists(RutaInfoCol3)) { File.Delete(RutaInfoCol3); }
                            if (File.Exists(RutaInfoCol4)) { File.Delete(RutaInfoCol4); }
                            if (File.Exists(RutaInfoCol5)) { File.Delete(RutaInfoCol5); }
                            if (File.Exists(RutaInfoCol6)) { File.Delete(RutaInfoCol6); }
                            if (File.Exists(RutaInfoCol7)) { File.Delete(RutaInfoCol7); }
                            if (File.Exists(RutaInfoCol8)) { File.Delete(RutaInfoCol8); }
                            if (File.Exists(Sem30_1)) { File.Delete(Sem30_1); }
                            if (File.Exists(Salariotxt1Global)) { File.Delete(Salariotxt1Global); }
                            if (File.Exists(Salariotxt2Global)) { File.Delete(Salariotxt2Global); }
                            Recorredor_Regiones = 1;
                            return;
                        }
                        else
                        {
                            System.Threading.Thread.Sleep(2000);
                            Verificador_Sindo_Activo();
                            if (Autorizador_Conexion == 0)
                            {
                                System.Threading.Thread.Sleep(700);
                                SendKeys.SendWait("%A"); //Esto activa el menu
                                SendKeys.SendWait("T"); //Tecla T conecta SINDO
                                System.Threading.Thread.Sleep(1200);
                                SendKeys.Send(REGION_SINDO); //Enviar el comando usando el objeto currentHost
                                SendKeys.Send("{ENTER}"); // Ejecuta "intro"
                            }
                        }
                    }

                    if (Autorizador_Conexion == 1)
                    {
                        hWnd_Ven = FindWindow(null, Ventana);
                        if (hWnd_Ven == IntPtr.Zero)
                        {
                            if (File.Exists(Ruta_Guardar_NSS)) { File.Delete(Ruta_Guardar_NSS); }
                            if (File.Exists(Ruta_NSS_Limpiado)) { File.Delete(Ruta_NSS_Limpiado); }
                            if (File.Exists(Ruta_Final_NSS)) { File.Delete(Ruta_Final_NSS); }
                            if (File.Exists(RutaInfoCol1)) { File.Delete(RutaInfoCol1); }
                            if (File.Exists(RutaInfoCol2)) { File.Delete(RutaInfoCol2); }
                            if (File.Exists(RutaInfoCol3)) { File.Delete(RutaInfoCol3); }
                            if (File.Exists(RutaInfoCol4)) { File.Delete(RutaInfoCol4); }
                            if (File.Exists(RutaInfoCol5)) { File.Delete(RutaInfoCol5); }
                            if (File.Exists(RutaInfoCol6)) { File.Delete(RutaInfoCol6); }
                            if (File.Exists(RutaInfoCol7)) { File.Delete(RutaInfoCol7); }
                            if (File.Exists(RutaInfoCol8)) { File.Delete(RutaInfoCol8); }
                            if (File.Exists(Sem30_1)) { File.Delete(Sem30_1); }
                            if (File.Exists(Salariotxt1Global)) { File.Delete(Salariotxt1Global); }
                            if (File.Exists(Salariotxt2Global)) { File.Delete(Salariotxt2Global); }
                            Recorredor_Regiones = 1;
                            return;
                        }
                        else
                        {
                            System.Threading.Thread.Sleep(iIdleTime); //Espera el tiempo necesario 
                            SendKeys.Send("{ENTER}");
                        }
                        hWnd_Ven = FindWindow(null, Ventana);
                        if (hWnd_Ven == IntPtr.Zero)
                        {
                            if (File.Exists(Ruta_Guardar_NSS)) { File.Delete(Ruta_Guardar_NSS); }
                            if (File.Exists(Ruta_NSS_Limpiado)) { File.Delete(Ruta_NSS_Limpiado); }
                            if (File.Exists(Ruta_Final_NSS)) { File.Delete(Ruta_Final_NSS); }
                            if (File.Exists(RutaInfoCol1)) { File.Delete(RutaInfoCol1); }
                            if (File.Exists(RutaInfoCol2)) { File.Delete(RutaInfoCol2); }
                            if (File.Exists(RutaInfoCol3)) { File.Delete(RutaInfoCol3); }
                            if (File.Exists(RutaInfoCol4)) { File.Delete(RutaInfoCol4); }
                            if (File.Exists(RutaInfoCol5)) { File.Delete(RutaInfoCol5); }
                            if (File.Exists(RutaInfoCol6)) { File.Delete(RutaInfoCol6); }
                            if (File.Exists(RutaInfoCol7)) { File.Delete(RutaInfoCol7); }
                            if (File.Exists(RutaInfoCol8)) { File.Delete(RutaInfoCol8); }
                            if (File.Exists(Sem30_1)) { File.Delete(Sem30_1); }
                            if (File.Exists(Salariotxt1Global)) { File.Delete(Salariotxt1Global); }
                            if (File.Exists(Salariotxt2Global)) { File.Delete(Salariotxt2Global); }
                            Recorredor_Regiones = 1;
                            return;
                        }
                        else
                        {
                            System.Threading.Thread.Sleep(iIdleTime);
                            SendKeys.Send("CESN");
                            SendKeys.Send("{ENTER}");
                        }
                        hWnd_Ven = FindWindow(null, Ventana);
                        if (hWnd_Ven == IntPtr.Zero)
                        {
                            if (File.Exists(Ruta_Guardar_NSS)) { File.Delete(Ruta_Guardar_NSS); }
                            if (File.Exists(Ruta_NSS_Limpiado)) { File.Delete(Ruta_NSS_Limpiado); }
                            if (File.Exists(Ruta_Final_NSS)) { File.Delete(Ruta_Final_NSS); }
                            if (File.Exists(RutaInfoCol1)) { File.Delete(RutaInfoCol1); }
                            if (File.Exists(RutaInfoCol2)) { File.Delete(RutaInfoCol2); }
                            if (File.Exists(RutaInfoCol3)) { File.Delete(RutaInfoCol3); }
                            if (File.Exists(RutaInfoCol4)) { File.Delete(RutaInfoCol4); }
                            if (File.Exists(RutaInfoCol5)) { File.Delete(RutaInfoCol5); }
                            if (File.Exists(RutaInfoCol6)) { File.Delete(RutaInfoCol6); }
                            if (File.Exists(RutaInfoCol7)) { File.Delete(RutaInfoCol7); }
                            if (File.Exists(RutaInfoCol8)) { File.Delete(RutaInfoCol8); }
                            if (File.Exists(Sem30_1)) { File.Delete(Sem30_1); }
                            if (File.Exists(Salariotxt1Global)) { File.Delete(Salariotxt1Global); }
                            if (File.Exists(Salariotxt2Global)) { File.Delete(Salariotxt2Global); }
                            Recorredor_Regiones = 1;
                            return;
                        }
                        else
                        {
                            System.Threading.Thread.Sleep(1700);
                            SendKeys.Send(USUARIO); //AQUI MANDAMOS EL USUARIO
                            SendKeys.Send("{ENTER}");
                        }
                        hWnd_Ven = FindWindow(null, Ventana);
                        if (hWnd_Ven == IntPtr.Zero)
                        {
                            if (File.Exists(Ruta_Guardar_NSS)) { File.Delete(Ruta_Guardar_NSS); }
                            if (File.Exists(Ruta_NSS_Limpiado)) { File.Delete(Ruta_NSS_Limpiado); }
                            if (File.Exists(Ruta_Final_NSS)) { File.Delete(Ruta_Final_NSS); }
                            if (File.Exists(RutaInfoCol1)) { File.Delete(RutaInfoCol1); }
                            if (File.Exists(RutaInfoCol2)) { File.Delete(RutaInfoCol2); }
                            if (File.Exists(RutaInfoCol3)) { File.Delete(RutaInfoCol3); }
                            if (File.Exists(RutaInfoCol4)) { File.Delete(RutaInfoCol4); }
                            if (File.Exists(RutaInfoCol5)) { File.Delete(RutaInfoCol5); }
                            if (File.Exists(RutaInfoCol6)) { File.Delete(RutaInfoCol6); }
                            if (File.Exists(RutaInfoCol7)) { File.Delete(RutaInfoCol7); }
                            if (File.Exists(RutaInfoCol8)) { File.Delete(RutaInfoCol8); }
                            if (File.Exists(Sem30_1)) { File.Delete(Sem30_1); }
                            if (File.Exists(Salariotxt1Global)) { File.Delete(Salariotxt1Global); }
                            if (File.Exists(Salariotxt2Global)) { File.Delete(Salariotxt2Global); }
                            Recorredor_Regiones = 1;
                            return;
                        }
                        else
                        {
                            Verificador_Sindo_Activo_Usuario();
                            if (Autorizador_Conexion_Usuario == 0)
                            {
                                IntPtr hWnd3 = FindWindow(null, "Extractor Informacion SINDO");
                                SetForegroundWindow(hWnd3);
                                MessageBox.Show("Puede que su sesion este activa en otro ordenador", "Calculadora 140Bis");
                                if (File.Exists(Ruta_Guardar_NSS)) { File.Delete(Ruta_Guardar_NSS); }
                                if (File.Exists(Ruta_NSS_Limpiado)) { File.Delete(Ruta_NSS_Limpiado); }
                                if (File.Exists(Ruta_Final_NSS)) { File.Delete(Ruta_Final_NSS); }
                                if (File.Exists(RutaInfoCol1)) { File.Delete(RutaInfoCol1); }
                                if (File.Exists(RutaInfoCol2)) { File.Delete(RutaInfoCol2); }
                                if (File.Exists(RutaInfoCol3)) { File.Delete(RutaInfoCol3); }
                                if (File.Exists(RutaInfoCol4)) { File.Delete(RutaInfoCol4); }
                                if (File.Exists(RutaInfoCol5)) { File.Delete(RutaInfoCol5); }
                                if (File.Exists(RutaInfoCol6)) { File.Delete(RutaInfoCol6); }
                                if (File.Exists(RutaInfoCol7)) { File.Delete(RutaInfoCol7); }
                                if (File.Exists(RutaInfoCol8)) { File.Delete(RutaInfoCol8); }
                                if (File.Exists(Sem30_1)) { File.Delete(Sem30_1); }
                                if (File.Exists(Salariotxt1Global)) { File.Delete(Salariotxt1Global); }
                                if (File.Exists(Salariotxt2Global)) { File.Delete(Salariotxt2Global); }
                                Recorredor_Regiones = 1;
                                return;
                            }
                            else
                            {
                                System.Threading.Thread.Sleep(iIdleTime);
                                SendKeys.Send(CONTRASEÑA); //AQUI MANDAMOS LA CONTRASEÑA
                                SendKeys.Send("{ENTER}");
                            }
                        }
                        hWnd_Ven = FindWindow(null, Ventana);
                        if (hWnd_Ven == IntPtr.Zero)
                        {
                            if (File.Exists(Ruta_Guardar_NSS)) { File.Delete(Ruta_Guardar_NSS); }
                            if (File.Exists(Ruta_NSS_Limpiado)) { File.Delete(Ruta_NSS_Limpiado); }
                            if (File.Exists(Ruta_Final_NSS)) { File.Delete(Ruta_Final_NSS); }
                            if (File.Exists(RutaInfoCol1)) { File.Delete(RutaInfoCol1); }
                            if (File.Exists(RutaInfoCol2)) { File.Delete(RutaInfoCol2); }
                            if (File.Exists(RutaInfoCol3)) { File.Delete(RutaInfoCol3); }
                            if (File.Exists(RutaInfoCol4)) { File.Delete(RutaInfoCol4); }
                            if (File.Exists(RutaInfoCol5)) { File.Delete(RutaInfoCol5); }
                            if (File.Exists(RutaInfoCol6)) { File.Delete(RutaInfoCol6); }
                            if (File.Exists(RutaInfoCol7)) { File.Delete(RutaInfoCol7); }
                            if (File.Exists(RutaInfoCol8)) { File.Delete(RutaInfoCol8); }
                            if (File.Exists(Sem30_1)) { File.Delete(Sem30_1); }
                            if (File.Exists(Salariotxt1Global)) { File.Delete(Salariotxt1Global); }
                            if (File.Exists(Salariotxt2Global)) { File.Delete(Salariotxt2Global); }
                            Recorredor_Regiones = 1;
                            return;
                        }
                        else
                        {
                            System.Threading.Thread.Sleep(iIdleTime);
                            SendKeys.Send("sz00");
                            SendKeys.Send("{ENTER}");
                        }
                        hWnd_Ven = FindWindow(null, Ventana);
                        if (hWnd_Ven == IntPtr.Zero)
                        {
                            if (File.Exists(Ruta_Guardar_NSS)) { File.Delete(Ruta_Guardar_NSS); }
                            if (File.Exists(Ruta_NSS_Limpiado)) { File.Delete(Ruta_NSS_Limpiado); }
                            if (File.Exists(Ruta_Final_NSS)) { File.Delete(Ruta_Final_NSS); }
                            if (File.Exists(RutaInfoCol1)) { File.Delete(RutaInfoCol1); }
                            if (File.Exists(RutaInfoCol2)) { File.Delete(RutaInfoCol2); }
                            if (File.Exists(RutaInfoCol3)) { File.Delete(RutaInfoCol3); }
                            if (File.Exists(RutaInfoCol4)) { File.Delete(RutaInfoCol4); }
                            if (File.Exists(RutaInfoCol5)) { File.Delete(RutaInfoCol5); }
                            if (File.Exists(RutaInfoCol6)) { File.Delete(RutaInfoCol6); }
                            if (File.Exists(RutaInfoCol7)) { File.Delete(RutaInfoCol7); }
                            if (File.Exists(RutaInfoCol8)) { File.Delete(RutaInfoCol8); }
                            if (File.Exists(Sem30_1)) { File.Delete(Sem30_1); }
                            if (File.Exists(Salariotxt1Global)) { File.Delete(Salariotxt1Global); }
                            if (File.Exists(Salariotxt2Global)) { File.Delete(Salariotxt2Global); }
                            Recorredor_Regiones = 1;
                            return;
                        }
                        else
                        {
                            Verificador_Sindo_Activo_Menu();
                            if (Autorizador_Conexion_Menu == 0)
                            {
                                return;
                            }
                            else
                            {
                                System.Threading.Thread.Sleep(iIdleTime);
                                SendKeys.Send("{F7}");
                            }
                        }
                        hWnd_Ven = FindWindow(null, Ventana);
                        if (hWnd_Ven == IntPtr.Zero)
                        {
                            if (File.Exists(Ruta_Guardar_NSS)) { File.Delete(Ruta_Guardar_NSS); }
                            if (File.Exists(Ruta_NSS_Limpiado)) { File.Delete(Ruta_NSS_Limpiado); }
                            if (File.Exists(Ruta_Final_NSS)) { File.Delete(Ruta_Final_NSS); }
                            if (File.Exists(RutaInfoCol1)) { File.Delete(RutaInfoCol1); }
                            if (File.Exists(RutaInfoCol2)) { File.Delete(RutaInfoCol2); }
                            if (File.Exists(RutaInfoCol3)) { File.Delete(RutaInfoCol3); }
                            if (File.Exists(RutaInfoCol4)) { File.Delete(RutaInfoCol4); }
                            if (File.Exists(RutaInfoCol5)) { File.Delete(RutaInfoCol5); }
                            if (File.Exists(RutaInfoCol6)) { File.Delete(RutaInfoCol6); }
                            if (File.Exists(RutaInfoCol7)) { File.Delete(RutaInfoCol7); }
                            if (File.Exists(RutaInfoCol8)) { File.Delete(RutaInfoCol8); }
                            if (File.Exists(Sem30_1)) { File.Delete(Sem30_1); }
                            if (File.Exists(Salariotxt1Global)) { File.Delete(Salariotxt1Global); }
                            if (File.Exists(Salariotxt2Global)) { File.Delete(Salariotxt2Global); }
                            Recorredor_Regiones = 1;
                            return;
                        }
                        else
                        {
                            if (NSS.Length > 10)
                            {
                                NSS = NSS.Substring(0, 10);
                            }
                            System.Threading.Thread.Sleep(iIdleTime);
                            SendKeys.Send(NSS);
                            SendKeys.Send("{ENTER}");
                        }
                        hWnd_Ven = FindWindow(null, Ventana);
                        if (hWnd_Ven == IntPtr.Zero)
                        {
                            if (File.Exists(Ruta_Guardar_NSS)) { File.Delete(Ruta_Guardar_NSS); }
                            if (File.Exists(Ruta_NSS_Limpiado)) { File.Delete(Ruta_NSS_Limpiado); }
                            if (File.Exists(Ruta_Final_NSS)) { File.Delete(Ruta_Final_NSS); }
                            if (File.Exists(RutaInfoCol1)) { File.Delete(RutaInfoCol1); }
                            if (File.Exists(RutaInfoCol2)) { File.Delete(RutaInfoCol2); }
                            if (File.Exists(RutaInfoCol3)) { File.Delete(RutaInfoCol3); }
                            if (File.Exists(RutaInfoCol4)) { File.Delete(RutaInfoCol4); }
                            if (File.Exists(RutaInfoCol5)) { File.Delete(RutaInfoCol5); }
                            if (File.Exists(RutaInfoCol6)) { File.Delete(RutaInfoCol6); }
                            if (File.Exists(RutaInfoCol7)) { File.Delete(RutaInfoCol7); }
                            if (File.Exists(RutaInfoCol8)) { File.Delete(RutaInfoCol8); }
                            if (File.Exists(Sem30_1)) { File.Delete(Sem30_1); }
                            if (File.Exists(Salariotxt1Global)) { File.Delete(Salariotxt1Global); }
                            if (File.Exists(Salariotxt2Global)) { File.Delete(Salariotxt2Global); }
                            Recorredor_Regiones = 1;
                            return;
                        }
                        else
                        {
                            bool Verificador_Copiar = true; //ESTA VARIABLE SERA FALSE CUANDO YA TERMINE O DEBA ACABAR DE COPIAR INFO DEL SINDO
                            int Validador_Sobreescribir_TXT = 0;
                            while (Verificador_Copiar == true)
                            {
                                if (Verificador_Copiar == true)
                                {
                                    Validador_Sobreescribir_TXT = 1;
                                }
                                else
                                {
                                    Validador_Sobreescribir_TXT = 0;
                                }
                                hWnd_Ven = FindWindow(null, Ventana);
                                if (hWnd_Ven == IntPtr.Zero)
                                {
                                    Verificador_Copiar = false;
                                    if (File.Exists(Ruta_Guardar_NSS)) { File.Delete(Ruta_Guardar_NSS); }
                                    if (File.Exists(Ruta_NSS_Limpiado)) { File.Delete(Ruta_NSS_Limpiado); }
                                    if (File.Exists(Ruta_Final_NSS)) { File.Delete(Ruta_Final_NSS); }
                                    if (File.Exists(RutaInfoCol1)) { File.Delete(RutaInfoCol1); }
                                    if (File.Exists(RutaInfoCol2)) { File.Delete(RutaInfoCol2); }
                                    if (File.Exists(RutaInfoCol3)) { File.Delete(RutaInfoCol3); }
                                    if (File.Exists(RutaInfoCol4)) { File.Delete(RutaInfoCol4); }
                                    if (File.Exists(RutaInfoCol5)) { File.Delete(RutaInfoCol5); }
                                    if (File.Exists(RutaInfoCol6)) { File.Delete(RutaInfoCol6); }
                                    if (File.Exists(RutaInfoCol7)) { File.Delete(RutaInfoCol7); }
                                    if (File.Exists(RutaInfoCol8)) { File.Delete(RutaInfoCol8); }
                                    if (File.Exists(Sem30_1)) { File.Delete(Sem30_1); }
                                    if (File.Exists(Salariotxt1Global)) { File.Delete(Salariotxt1Global); }
                                    if (File.Exists(Salariotxt2Global)) { File.Delete(Salariotxt2Global); }
                                    Recorredor_Regiones = 1;
                                    return;
                                }
                                else
                                {
                                    Copiar_Pegar(Validador_Sobreescribir_TXT);
                                }
                                hWnd_Ven = FindWindow(null, Ventana);
                                if (hWnd_Ven == IntPtr.Zero)
                                {
                                    Verificador_Copiar = false;
                                    if (File.Exists(Ruta_Guardar_NSS)) { File.Delete(Ruta_Guardar_NSS); }
                                    if (File.Exists(Ruta_NSS_Limpiado)) { File.Delete(Ruta_NSS_Limpiado); }
                                    if (File.Exists(Ruta_Final_NSS)) { File.Delete(Ruta_Final_NSS); }
                                    if (File.Exists(RutaInfoCol1)) { File.Delete(RutaInfoCol1); }
                                    if (File.Exists(RutaInfoCol2)) { File.Delete(RutaInfoCol2); }
                                    if (File.Exists(RutaInfoCol3)) { File.Delete(RutaInfoCol3); }
                                    if (File.Exists(RutaInfoCol4)) { File.Delete(RutaInfoCol4); }
                                    if (File.Exists(RutaInfoCol5)) { File.Delete(RutaInfoCol5); }
                                    if (File.Exists(RutaInfoCol6)) { File.Delete(RutaInfoCol6); }
                                    if (File.Exists(RutaInfoCol7)) { File.Delete(RutaInfoCol7); }
                                    if (File.Exists(RutaInfoCol8)) { File.Delete(RutaInfoCol8); }
                                    if (File.Exists(Sem30_1)) { File.Delete(Sem30_1); }
                                    if (File.Exists(Salariotxt1Global)) { File.Delete(Salariotxt1Global); }
                                    if (File.Exists(Salariotxt2Global)) { File.Delete(Salariotxt2Global); }
                                    Recorredor_Regiones = 1;
                                    return;
                                }
                                else
                                {
                                    Verificador_Copiar = ComprobacionSindo(Verificador_Copiar);

                                    SendKeys.Send("+{F9}"); // Avanzar
                                    keybd_event(VK_NUMLOCK, 0x45, KEYEVENTF_EXTENDEDKEY, 0);
                                    keybd_event(VK_NUMLOCK, 0x45, KEYEVENTF_EXTENDEDKEY | KEYEVENTF_KEYUP, 0);
                                }
                            }
                        }
                    }
                    else
                    {
                        MessageBox.Show("La Sesion de SINDO se ha desconectado", "Calculadora 140Bis");
                    }
                    Conectar_Desconectar();
                    Region_Obtenida = Recorredor_Regiones;
                    Recorredor_Regiones = Recorredor_Regiones + 1;
                }
                if (Recorredor_Regiones == 4)
                {
                    Limpiar();
                }
            }
            catch (Exception ex)
            {
                IntPtr hWnd3 = FindWindow(null, "Calculadora 140Bis");
                SetForegroundWindow(hWnd3);
                MessageBox.Show("Error: " + ex.Message, "Calculadora 140Bis");
            }
        }

        private static void Copiar_Pegar(int Validador_Sobreescribir_TXT)
        {
            try
            {
                IntPtr hWnd = FindWindow(null, Ventana);
                if (hWnd == IntPtr.Zero)
                {
                    if (File.Exists(Ruta_Guardar_NSS)) { File.Delete(Ruta_Guardar_NSS); }
                    if (File.Exists(Ruta_NSS_Limpiado)) { File.Delete(Ruta_NSS_Limpiado); }
                    if (File.Exists(Ruta_Final_NSS)) { File.Delete(Ruta_Final_NSS); }
                    if (File.Exists(RutaInfoCol1)) { File.Delete(RutaInfoCol1); }
                    if (File.Exists(RutaInfoCol2)) { File.Delete(RutaInfoCol2); }
                    if (File.Exists(RutaInfoCol3)) { File.Delete(RutaInfoCol3); }
                    if (File.Exists(RutaInfoCol4)) { File.Delete(RutaInfoCol4); }
                    if (File.Exists(RutaInfoCol5)) { File.Delete(RutaInfoCol5); }
                    if (File.Exists(RutaInfoCol6)) { File.Delete(RutaInfoCol6); }
                    if (File.Exists(RutaInfoCol7)) { File.Delete(RutaInfoCol7); }
                    if (File.Exists(RutaInfoCol8)) { File.Delete(RutaInfoCol8); }
                    if (File.Exists(Sem30_1)) { File.Delete(Sem30_1); }
                    if (File.Exists(Salariotxt1Global)) { File.Delete(Salariotxt1Global); }
                    if (File.Exists(Salariotxt2Global)) { File.Delete(Salariotxt2Global); }
                    Recorredor_Regiones = 1;
                    return;
                }
                else
                {
                    bool BloqNum = Control.IsKeyLocked(Keys.NumLock);
                    if (BloqNum)
                    {
                        //activado
                        keybd_event(VK_NUMLOCK, 0x45, KEYEVENTF_EXTENDEDKEY, 0);
                        keybd_event(VK_NUMLOCK, 0x45, KEYEVENTF_EXTENDEDKEY | KEYEVENTF_KEYUP, 0);

                        SendKeys.Send("^{CLEAR}"); //simular el ctrl + CLEAR que seleecionatodo
                    }
                    else
                    {
                        //desactivado
                        SendKeys.Send("^{CLEAR}"); //simular el ctrl + CLEAR que seleecionatodo
                    }

                    System.Windows.Forms.Clipboard.Clear();
                    System.Threading.Thread.Sleep(1200);
                    SendKeys.SendWait("^c"); //COPIAR INFO, ESTE FUE EL BUENO
                    System.Threading.Thread.Sleep(1700);
                    var Informacion_Copiada_Sindo = System.Windows.Forms.Clipboard.GetText();

                    if (Informacion_Copiada_Sindo != null)
                    {
                        GuardarContenidoDelPortapapeles(Informacion_Copiada_Sindo, Validador_Sobreescribir_TXT);
                    }
                    else
                    {
                        MessageBox.Show("No se pudo guardar el contenido del portapapeles", "Comandos/Copiar Pegar");
                    }
                }
            }
            catch (Exception ex)
            {
                IntPtr hWnd3 = FindWindow(null, "Calculadora 140Bis");
                SetForegroundWindow(hWnd3);
                MessageBox.Show("Error: " + ex.Message, "Calculadora 140Bis");
            }
        }

        public static void GuardarContenidoDelPortapapeles(string Informacion_Copiada_Sindo, int Validador_Sobreescribir_TXT) //GUARDA LO QUE HAY EN EL PORTAPAPELES
        {
            try
            {
                if (Validador_Sobreescribir_TXT == 1 && File.Exists(Ruta_Guardar_NSS))// COMPROBACION PARA ESCRIBIR TXT2 es 1 cuando no es el fin.
                {
                    File.AppendAllText(Ruta_Guardar_NSS, Informacion_Copiada_Sindo); //ESTO AGARRA EL TXT Y LE AGREGA LO QUE QUERAMOS
                    //File.WriteAllText(RutaInfo, info); //ESTO SOBREESCRIBE EL TXT CON LO QUE TENGAMOS
                }
                else if (!File.Exists(Ruta_Guardar_NSS))
                {
                    using (StreamWriter writer = new StreamWriter(Ruta_Guardar_NSS)) //TXT DEL PORTAPAPELES
                    {
                        writer.WriteLine(Informacion_Copiada_Sindo); //AQUI GUARDA LO QUE TENGA EL PORTAPAPELES.
                    }
                }
                else
                {
                    MessageBox.Show("Ya acabo", "Guardar Contenido");
                }
            }
            catch (Exception ex)
            {
                IntPtr hWnd3 = FindWindow(null, "Calculadora 140Bis");
                SetForegroundWindow(hWnd3);
                MessageBox.Show("Ocurrio un error al guardar el archivo" + ex.Message, "Calculadora 140Bis");
            }
        }

        private static bool ComprobacionSindo(bool Verificador_Copiar)
        {
            //throw new NotImplementedException();
            var lineas = File.ReadAllLines(Ruta_Guardar_NSS); //AQUI ESTA LEYENDO CUANTAS LINEAS TIENE

            string ultimalinea = lineas[lineas.Length - 3]; //AQUI EXTRAEMOS LA "ULTIMA" LINEA DEL TXT PARA COMPROBACION
            string ultimalinea2 = lineas[lineas.Length - 2];
            if (ultimalinea == " ,FIN DE PERIODOS" || ultimalinea == " ,ES EL FIN DE PERIODOS" ||
                ultimalinea2 == " ,FIN DE PERIODOS" || ultimalinea2 == " ,ES EL FIN DE PERIODOS" ||
                ultimalinea == " ,ERROR=>REGISTRO NO LOCALIZADO" || ultimalinea2 == " ,ERROR=>REGISTRO NO LOCALIZADO")
            {
                Region_Obtenida = 0;
                Verificador_Copiar = false;
                return Verificador_Copiar;
            }
            else
            {
                Verificador_Copiar = true;
                return Verificador_Copiar;
            }
        }

        public static void Limpiar()
        {
            string[] palabrasIgnoradas = {",S.IN.D.O.", ",NUM.", ",ASEG.", ",CURP", "               ,",",DEL", " ,NUM.", " ,DIGITE"
                                         ," ,FIN", " ,ERROR=>REGISTRO"};
            // ",NUM. DE SEG. SOC.  :,", ",DIGITE NUMERO DE SEG. SOC. Y REGISTRO PATRONAL", ",FIN DE PERIODOS", "ERROR=>REGISTRO NO LOCALIZADO",

            //,07,A6840266105,06/11/2024,4 7,01/11/2024,1154.16 M,0 ,NO ,NO,4 7,30/11/2024,1,
            //0 1       2           3     4        5        6     7   8   9  10      11    12 13
            int Verificador_NombreAsegurado = 0, Verificador_Curp = 0;
            int[] ColumnasSeleccionadas = { 2, 5, 6, 10, 11 };
            try
            {
                using (StreamReader reader = new StreamReader(Ruta_Guardar_NSS))
                using (StreamWriter writer = new StreamWriter(Ruta_NSS_Limpiado))
                {
                    string linea;
                    while ((linea = reader.ReadLine()) != null)
                    {
                        int Verificador_PalabraIgnorada = 1;
                        if (string.IsNullOrWhiteSpace(linea) || linea.Replace(",", "").Trim().Length == 0) //AQUI SI VEMOS FILAS VACIAS LAS IGNORAMOS
                        {
                            continue;
                        }

                        if (linea.StartsWith(",ASEG.") && Verificador_NombreAsegurado == 0)
                        {
                            NOMBRE_ASEGURADO = linea;
                            int i = NOMBRE_ASEGURADO.Length;
                            NOMBRE_ASEGURADO = NOMBRE_ASEGURADO.Substring(8, i - 38);
                            if (string.IsNullOrWhiteSpace(NOMBRE_ASEGURADO))
                            {
                                Verificador_NombreAsegurado = 0;
                            }
                            else
                            {
                                NOMBRE_ASEGURADO = NOMBRE_ASEGURADO.Replace('-', ' ');
                                Verificador_NombreAsegurado = 1;
                            }
                        }
                        if (linea.StartsWith(",CURP") && Verificador_Curp == 0)
                        {
                            CURP = linea;
                            if (CURP == ",CURP  ,") //",CURP  ,"
                            {
                                Verificador_Curp = 0;
                            }
                            else
                            {
                                CURP = CURP.Substring(8, 18);
                                Verificador_Curp = 1;
                            }
                        }
                        foreach (string palabra in palabrasIgnoradas)
                        {
                            if (linea.StartsWith(palabra))
                            {
                                Verificador_PalabraIgnorada = 0; //Verificador_PalabraIgnorada es si encuentra la palabra romba el ciclo y entre
                                //en la condicion de abajo 
                                break;
                            }
                        }
                        if (Verificador_PalabraIgnorada == 0)
                        {
                            continue;
                        }

                        string[] columnas = linea.Split(','); // AQUI AGARRAMOS LAS COLUMNAS INDICANDOLE COMO SE SEPARAN

                        List<string> columnasFiltradas = new List<string>();
                        foreach (int indice in ColumnasSeleccionadas)
                        {
                            if (indice < columnas.Length)
                            {
                                columnasFiltradas.Add(columnas[indice].Trim());
                            }
                        }
                        string nuevaLinea = string.Join(",", columnasFiltradas);

                        writer.WriteLine(nuevaLinea);
                    }
                }
                //********************************************************************************
                var lineas = File.ReadAllLines(Ruta_NSS_Limpiado);
                var lineasOrdenadas = lineas.Where(linea => !string.IsNullOrWhiteSpace(linea)).Select(linea => new
                {
                    Linea = linea,
                    UltimaFecha = ObtenerUltimaFecha(linea)
                }).OrderByDescending(x => x.UltimaFecha).Select(x => x.Linea).ToList();

                File.WriteAllLines(Ruta_Final_NSS, lineasOrdenadas);
                //**********************************************************************************
                Salario();
                Comprobaciones140();
            }
            catch (Exception ex)
            {
                IntPtr hWnd3 = FindWindow(null, "Calculadora 140Bis");
                SetForegroundWindow(hWnd3);
                MessageBox.Show("Error: " + ex.Message, "Calculadora 140Bis");
            }
        }

        static DateTime ObtenerUltimaFecha(string linea) //Aqui se ordena el txt por fecha
        {
            try
            {
                var columnas = linea.Split(',');
                string ultimaFecha = columnas.Last().Trim();

                if (ultimaFecha == "00/00/0000")
                {
                    ultimaFecha = DateTime.Now.ToString("dd/MM/yyyy");
                }

                return
                    DateTime.ParseExact(ultimaFecha, "dd/MM/yyyy", CultureInfo.InvariantCulture);
            }
            catch
            {
                return DateTime.MinValue;
            }
        }

        public static void Salario()
        {
            try
            {
                string linea;
                int contador = 0;
                DateTime AnterioInicial = DateTime.Today.AddDays(+2);
                DateTime AnteriorFinal = DateTime.Today.AddDays(+2);
                DateTime ActualFinal = DateTime.Today;
                DateTime ActualInicial = DateTime.Today;
                DateTime Limite = DateTime.Today.AddYears(-1);

                List<string> ListaRP = new List<string>();
                using (StreamReader reader = new StreamReader(Ruta_Final_NSS))
                using (StreamWriter writer = new StreamWriter(Salariotxt))
                {
                    while (ActualFinal > Limite || ActualInicial > Limite)
                    {
                        linea = reader.ReadLine();
                        string[] columnas = linea.Split(',');
                        string RPL = columnas[0].Trim();
                        string Salario = columnas[2].Trim();
                        int IndiRP = 0;
                        for (int i = Salario.Length - 1; i >= 0; i--)
                        {
                            if (char.IsLetter(Salario[i]))
                            {
                                Salario = Salario.Substring(0, i).Trim();
                                break;
                            }
                        }
                        string ActualFinal1 = columnas[4].Trim();
                        if (ActualFinal1 == "00/00/0000")
                        {
                            ActualFinal = DateTime.Today;
                        }
                        else
                        {
                            ActualFinal = DateTime.ParseExact(columnas[4].Trim(), "dd/MM/yyyy", CultureInfo.InvariantCulture);
                        }
                        ActualInicial = DateTime.ParseExact(columnas[1].Trim(), "dd/MM/yyyy", CultureInfo.InvariantCulture);

                        if (AnteriorFinal > DateTime.Today && AnterioInicial > DateTime.Today)
                        {
                            AnterioInicial = ActualInicial; AnteriorFinal = ActualFinal;
                            /*RPACTUAL = RPL;*/
                        }
                        if (contador == 0)
                        {
                            if (AnteriorFinal == DateTime.Today)
                            {
                                writer.WriteLine(Salario);
                                //RPANTERIOR = RPACTUAL;
                                contador = 1;
                                ListaRP.Add(RPL);
                                continue;
                            }
                            else
                            {
                                writer.WriteLine(Salario);
                                //RPANTERIOR = RPACTUAL;
                                contador = 1;
                                ListaRP.Add(RPL);
                                continue;
                            }
                        }
                        else
                        {
                            foreach (string DatoRP in ListaRP)
                            {
                                if (RPL == DatoRP)
                                {
                                    IndiRP = 1;
                                    break;
                                }
                            }
                            if (IndiRP == 1) //Aqui ve si el RP ya estaba 
                            {
                                if (ActualInicial < Limite && ActualFinal < Limite)
                                {
                                    continue;
                                }
                                if (ActualInicial < AnterioInicial && ActualFinal > AnterioInicial)
                                {
                                    AnterioInicial = ActualInicial;
                                }
                                if (ActualInicial == AnterioInicial)
                                {
                                    if (ActualFinal > AnteriorFinal)
                                    {
                                        AnteriorFinal = ActualFinal;
                                    }
                                }
                                if (AnteriorFinal == ActualFinal)
                                {
                                    if (ActualInicial < AnterioInicial)
                                    {
                                        AnterioInicial = ActualInicial;
                                    }
                                }
                                if (AnterioInicial < ActualInicial)
                                {
                                    if (ActualFinal > AnteriorFinal)
                                    {
                                        AnteriorFinal = ActualFinal;
                                    }
                                }
                                if (AnterioInicial == ActualFinal.AddDays(+1))
                                {
                                    AnterioInicial = ActualInicial;
                                }
                                if (AnterioInicial == ActualFinal)
                                {
                                    AnterioInicial = ActualInicial;
                                }

                                continue;
                            }
                            //*****************************************************************************
                            //Aqui ve si el RP no estaba
                            if (ActualInicial < Limite && ActualFinal < Limite)
                            {
                                continue;
                            }

                            if (ActualInicial < AnterioInicial && ActualFinal > AnterioInicial)
                            {
                                AnterioInicial = ActualInicial;
                                writer.WriteLine(Salario);
                                ListaRP.Add(RPL);
                                continue;
                            }
                            if (ActualInicial == AnterioInicial)
                            {
                                if (ActualFinal > AnteriorFinal)
                                {
                                    AnteriorFinal = ActualFinal;
                                    writer.WriteLine(Salario);
                                    ListaRP.Add(RPL);
                                    continue;
                                }
                            }
                            if (AnteriorFinal == ActualFinal)
                            {
                                if (ActualInicial < AnterioInicial)
                                {
                                    AnterioInicial = ActualInicial;
                                    writer.WriteLine(Salario);
                                    ListaRP.Add(RPL);
                                    continue;
                                }
                            }
                            if (AnterioInicial < ActualInicial)
                            {
                                if (ActualFinal > AnteriorFinal)
                                {
                                    AnteriorFinal = ActualFinal;
                                    writer.WriteLine(Salario);
                                    ListaRP.Add(RPL);
                                    continue;
                                }
                            }
                            if (AnterioInicial == ActualFinal)
                            {
                                AnterioInicial = ActualInicial;
                                writer.WriteLine(Salario);
                                ListaRP.Add(RPL);
                                continue;
                            }
                            if (ActualInicial > AnterioInicial && ActualFinal < AnteriorFinal)
                            {
                                writer.WriteLine(Salario);
                                ListaRP.Add(RPL);
                                continue;
                            }
                            if (ActualInicial < AnterioInicial && ActualFinal < AnterioInicial)
                            {
                                continue;
                            }
                            else
                            {
                                writer.WriteLine(Salario);
                                ListaRP.Add(RPL);
                                continue;
                            }
                        }
                    }
                    writer.Close();
                    reader.Close();
                }
            }
            catch (Exception ex)
            {
                IntPtr hWnd3 = FindWindow(null, "Calculadora 140Bis");
                SetForegroundWindow(hWnd3);
                MessageBox.Show("Error: " + ex.Message, "Calculadora 140Bis");
            }
        }

        public static void Comprobaciones140()
        { //viene de limpiar
            
            int Validador = 0;
            try
            {
                string linea0, linea;
                string ValidacionVigencia;
                int ABR = 1;
                using (StreamReader reader = new StreamReader(Ruta_Final_NSS))
                {
                    while (ABR == 1)
                    {//AQUI ESTAMOS CHECANDO SI ES VALIDO O NO

                        linea0 = reader.ReadLine();
                        string[] columnas1 = linea0.Split(',');
                        ValidacionVigencia = columnas1[4].Trim();
                        
                        if (ValidacionVigencia == "00/00/0000")
                        {
                            ValidacionVigencia = DateTime.Now.ToString("dd/MM/yyyy");
                        }
                        DateTime V = DateTime.Parse(ValidacionVigencia);
                        if (ValidacionVigencia == DateTime.Now.ToString("dd/MM/yyyy") || Fecha_Inicio_Licencia < V)
                        {
                            Validador = 1; ABR = 0;
                            break;
                        }
                        else
                        {
                            ABR = 0;
                            break;
                        }
                    }
                    reader.Close();
                }
                using (StreamReader reader = new StreamReader(Ruta_Final_NSS))
                using (StreamWriter writer = new StreamWriter(RutaInfoCol1)) //Info_1columnas
                {
                    if (Validador == 1)//Primera validacion "si tiene derecho"
                    {
                        while ((linea = reader.ReadLine()) != null)
                        {
                            string[] columnas = linea.Split(',');
                            string validacion1 = columnas[1].Trim();
                            string validacion2 = columnas[4].Trim();
                            string RegistroP = columnas[0].Trim();
                            string Mod = RegistroP.Substring(8, 2);

                            //MessageBox.Show("Tiene derecho", "Comprobaciones");

                            if (Mod == "10" || Mod == "13" || Mod == "14" || Mod == "17" || Mod == "30" || Mod == "31" ||
                                Mod == "34" || Mod == "35" || Mod == "40" || Mod == "42" || Mod == "43" || Mod == "44")
                            {
                                if (linea.Split(',')[2].Trim() == "0.00 F") //AQUI QUITAMOS LAS SEMANAS NO COTIZADAS
                                { continue; }
                                DateTime Inicio = DateTime.Parse(validacion1);
                                if (Mod == "40" && validacion2 == "00/00/0000")
                                {
                                    continue;
                                }//AGREGADO 18/12/24. SI ES MOD 40 PARA VIGENCIA SE DESCARTA EXCEPTO PARA CONTEO.
                                if (validacion2 == "00/00/0000")
                                {
                                    validacion2 = DateTime.Now.ToString("dd/MM/yyyy");
                                }
                                DateTime Final = DateTime.Parse(validacion2);
                                writer.WriteLine(validacion1 + " , " + validacion2);
                            }
                            else
                            { continue; } //AQUI QUITAMOS LAS MODALIDADES NO "VALIDAS"
                            //break; //No recuerdo pa que es
                        }
                    }
                    else
                    {
                        MessageBox.Show("No tiene Derecho", "Calculadora 140Bis");
                    }
                }
                if (Validador == 1) //AQUI SI TIENE DERECHO PROCEDERA, SINO ALV.
                {
                    //AQUI VAMOS A ORDENARLO POR FECHA
                    List<string[]> filas = new List<string[]>();
                    using (StreamReader reader = new StreamReader(RutaInfoCol1)) // Info_1columnas
                    {
                        string linea1;
                        while ((linea1 = reader.ReadLine()) != null)
                        {
                            string[] columnas = linea1.Split(',');
                            filas.Add(columnas);
                        }
                    }

                    filas.Sort((x, y) =>
                    {
                        DateTime fechaX, fechaY;
                        if (DateTime.TryParse(x[1].Trim(), out fechaX) && DateTime.TryParse(y[1].Trim(), out fechaY))
                        { return fechaX.CompareTo(fechaY); }
                        return 0;
                    });

                    //AQUI CONCATENA LAS FECHAS EN CASO DE QUE EL FIN DE LA ACTUAL Y EL INICIO DE LA ANTERIOR SE LLEVEN POR 1 DIA
                    using (StreamWriter writer = new StreamWriter(RutaInfoCol2)) //Info_2Concatenado
                    {
                        DateTime? currentInicio = null;
                        DateTime? currentFin = null;
                        //DateTime filtro = new DateTime(2022, 1, 1);
                        DateTime filtro = Fecha_Diagnostico.AddYears(-1); //ESTO ESTA BIEN NO SE MUEVE
                        filas.Reverse();
                        foreach (var fila in filas)
                        {
                            // Convierte las fechas de texto a DateTime
                            DateTime inicio = DateTime.ParseExact(fila[0].Trim(), "dd/MM/yyyy", CultureInfo.InvariantCulture);
                            DateTime fin = DateTime.ParseExact(fila[1].Trim(), "dd/MM/yyyy", CultureInfo.InvariantCulture);

                            if (inicio > fin)
                            {
                                continue;
                            } //Agregado 17/01/2025 para quitar periodos ilogicos

                            if (inicio < filtro) //LIMITAMOS LA FECHA PARA MOSTRAR SOLO LAS MAS RECIENTES
                            { //AQUI ORIGINALMENTE IBA INICIO Y NO FIN < FILTRO 09/12/24
                                if (filtro > inicio && filtro < fin)
                                {
                                    inicio = filtro;
                                }
                                else
                                {
                                    continue;
                                }
                            }

                            // Si es la primera línea, inicializamos las fechas
                            if (currentInicio == null)
                            {
                                currentInicio = inicio;
                                currentFin = fin;
                            }
                            else
                            {
                                // Verificamos si la fecha final de la fila anterior es el día anterior a la fecha de inicio de la fila actual
                                if (currentInicio.Value == fin.AddDays(1))
                                {
                                    // Si las fechas son consecutivas, fusionamos los rangos
                                    currentInicio = inicio < currentInicio.Value ? inicio : currentInicio;
                                }
                                else
                                {
                                    // Si no se pueden fusionar, escribimos el rango actual
                                    writer.WriteLine(currentInicio.Value.ToString("dd/MM/yyyy") + ", " + currentFin.Value.ToString("dd/MM/yyyy"));

                                    // Reiniciamos las fechas con la nueva fila
                                    currentInicio = inicio;
                                    currentFin = fin;
                                }
                            }
                        }
                        // Escribimos el último par de fechas, si existe
                        if (currentInicio != null && currentFin != null)
                        {
                            writer.WriteLine(currentInicio.Value.ToString("dd/MM/yyyy") + ", " + currentFin.Value.ToString("dd/MM/yyyy"));
                        }
                    }

                    //AQUI COMIENZA A TRASLAPAR BIEN LAS FECHAS.
                    List<string[]> filas2 = new List<string[]>();
                    using (StreamReader reader = new StreamReader(RutaInfoCol2)) //Info_2Concatenado
                    {
                        string linea2;
                        while ((linea2 = reader.ReadLine()) != null)
                        {
                            string[] columnas = linea2.Split(',');
                            filas2.Add(columnas);
                        }
                    }
                    using (StreamWriter writer = new StreamWriter(RutaInfoCol3)) //Info_3Traslapado
                    {
                        //filas2.Reverse();
                        DateTime? currentInicio = null;
                        DateTime? currentFin = null;
                        //DateTime filtro1 = Fecha_Inicio_Lic;
                        foreach (var fila in filas2)
                        {
                            // Convierte las fechas de texto a DateTime
                            DateTime inicio = DateTime.ParseExact(fila[0].Trim(), "dd/MM/yyyy", CultureInfo.InvariantCulture);
                            DateTime fin = DateTime.ParseExact(fila[1].Trim(), "dd/MM/yyyy", CultureInfo.InvariantCulture);

                            // Si es la primera línea, inicializamos las fechas
                            if (currentInicio == null)
                            {
                                currentInicio = inicio;
                                currentFin = fin;
                            }
                            else
                            {
                                if (inicio == fin) { continue; }
                                if (currentInicio.Value == inicio && currentFin.Value == fin) { continue; } //Agregado 11/12/2024
                                if (fin > currentInicio.Value && fin < currentFin.Value)
                                {
                                    if (currentInicio.Value == inicio)
                                    { currentInicio = inicio; }

                                    if (currentInicio.Value < inicio && currentFin > fin)
                                    {
                                        writer.WriteLine(currentInicio.Value.ToString("dd/MM/yyyy") + " , " + currentFin.Value.ToString("dd/MM/yyyy"));
                                        continue;

                                    } //Agregado 11/12/2024
                                    if (inicio > currentInicio.Value && fin > currentInicio.Value && inicio < currentFin.Value && fin < currentFin.Value)
                                    { continue; } //Modificado 11/12/2024

                                    if (currentInicio > inicio && currentInicio < fin && currentFin > fin)
                                    {
                                        currentInicio = inicio;
                                    } //AGREGADO 17/12/2024

                                    else
                                    {
                                        currentFin = currentInicio.Value;
                                        currentInicio = inicio;
                                    }

                                    writer.WriteLine(currentInicio.Value.ToString("dd/MM/yyyy") + " , " + currentFin.Value.ToString("dd/MM/yyyy"));
                                }
                                else
                                {

                                    if (currentFin == fin)
                                    {
                                        if (inicio < currentInicio)
                                        {
                                            currentInicio = inicio;
                                        }
                                        else
                                        {
                                            writer.WriteLine(currentInicio.Value.ToString("dd/MM/yyyy") + " , " + currentFin.Value.ToString("dd/MM/yyyy"));
                                        }
                                    } //Agregado 18/12/24
                                    if (currentFin.Value > fin && inicio < currentFin.Value)
                                    {
                                        writer.WriteLine(currentInicio.Value.ToString("dd/MM/yyyy") + " , " + currentFin.Value.ToString("dd/MM/yyyy"));
                                    }//Agregado 11/12/2024
                                    currentInicio = inicio;
                                    currentFin = fin;
                                    writer.WriteLine(currentInicio.Value.ToString("dd/MM/yyyy") + " , " + currentFin.Value.ToString("dd/MM/yyyy"));
                                }
                                //writer.WriteLine(currentInicio.Value.ToString("dd/MM/yyyy") + " , " + currentFin.Value.ToString("dd/MM/yyyy"));
                            }
                        }
                        //Escribimos el último par de fechas, si existe, VERE SI NO LO BORRO
                        if (currentInicio != null && currentFin != null)
                        {
                            writer.WriteLine(currentInicio.Value.ToString("dd/MM/yyyy") + " , " + currentFin.Value.ToString("dd/MM/yyyy"));
                        }
                    }
                    //TRASLAPE FINAL DE FECHAS
                    List<string[]> filas3 = new List<string[]>();
                    using (StreamReader reader = new StreamReader(RutaInfoCol3)) //Info_3Traslapado
                    using (StreamWriter writer = new StreamWriter(RutaInfoCol4)) //4Final
                    {
                        string linea3;
                        DateTime filtro30 = Fecha_Inicio_Licencia.AddDays(-1);//ESTO ESTA BIEN NO SE MUEVE
                        DateTime filtrodiag = Fecha_Diagnostico.AddYears(-1);  //AGREGADO 9/12/24
                        DateTime? currentInicio2 = null;
                        DateTime? currentFin2 = null;
                        while ((linea3 = reader.ReadLine()) != null)
                        {
                            string[] columnas = linea3.Split(',');
                            filas3.Add(columnas);
                        }

                        foreach (var fila3 in filas3)
                        {
                            // Convierte las fechas de texto a DateTime
                            DateTime inicio2 = DateTime.ParseExact(fila3[0].Trim(), "dd/MM/yyyy", CultureInfo.InvariantCulture);
                            DateTime fin2 = DateTime.ParseExact(fila3[1].Trim(), "dd/MM/yyyy", CultureInfo.InvariantCulture);

                            if (inicio2 > filtro30) //LIMITAMOS LA FECHA PARA MOSTRAR SOLO LAS MAS RECIENTES
                            { continue; }

                            // Si es la primera línea, inicializamos las fechas
                            if (currentInicio2 == null)
                            {
                                currentInicio2 = inicio2;
                                currentFin2 = fin2;
                            }
                            else
                            {
                                if (inicio2 == currentInicio2.Value && fin2 == currentFin2.Value) //evitar duplicitad de fechas?
                                { continue; }

                                if (currentInicio2.Value == fin2)
                                { currentInicio2 = inicio2; }

                                if (inicio2 < filtrodiag && fin2 > filtrodiag)
                                { currentInicio2 = filtrodiag; /*ESTO LO AGREGUE EL 9/12/2024*/ }

                                if (inicio2 == currentInicio2.Value && fin2 < currentFin2.Value)
                                { continue; }

                                if (currentFin2 == fin2)
                                {
                                    if (inicio2 < currentInicio2)
                                    {
                                        currentInicio2 = inicio2;
                                        continue;
                                    }
                                    else
                                    {
                                        continue;
                                    }
                                } //Agregado 18/12/24

                                if (inicio2 < currentInicio2.Value && fin2 < currentFin2.Value && inicio2 < filtrodiag && fin2 > filtrodiag)
                                { continue; }//ESTO LO AGREGUE EL 9/12/2024
                                else
                                {
                                    writer.WriteLine(currentInicio2.Value.ToString("dd/MM/yyyy") + " , " + currentFin2.Value.ToString("dd/MM/yyyy"));
                                    currentInicio2 = inicio2;
                                    currentFin2 = fin2;
                                }
                            }
                        }
                        //Escribimos el último par de fechas, si existe, VERE SI NO LO BORRO
                        if (currentInicio2 != null && currentFin2 != null)
                        {
                            writer.WriteLine(currentInicio2.Value.ToString("dd/MM/yyyy") + " , " + currentFin2.Value.ToString("dd/MM/yyyy"));
                        }
                    }

                    string ruta_Final = RutaInfoCol4;
                    string contenido = File.ReadAllText(ruta_Final).Trim();
                    if (string.IsNullOrEmpty(contenido))
                    {
                        ruta_Final = RutaInfoCol3;
                    }
                    
                    //SE USARA PARA LA VERIFICACION DE LAS SEMANAS.
                    using (StreamReader reader = new StreamReader(ruta_Final))
                    using (StreamWriter writer1 = new StreamWriter(RutaInfoCol5))
                    {
                        string linea4; int DiasTotales = 0, DiasTotales2 = 0, ajus1 = 0, ajus2 = 0, ajuste3 = 0, ajus3 = 0, nej = 0;
                        DateTime FiltroFinal2 = DateTime.Now.Date;
                        DateTime FiltroInicial2 = DateTime.Now.Date;
                        while ((linea4 = reader.ReadLine()) != null)
                        {
                            string[] COL = linea4.Split(',');
                            string NUM1 = COL[0].Trim();
                            string NUM2 = COL[1].Trim();
                            DateTime Inicio = DateTime.Parse(NUM1);
                            DateTime Final = DateTime.Parse(NUM2);
                            DateTime filtro = Fecha_Diagnostico;
                            if (filtro > Inicio && filtro < Final) //Aqui estamos recortando para que no agarre el periodo completo
                            //en caso de que encuentre una fecha donde el inicio este antes del filtro
                            { Final = filtro; }

                            if (Inicio > filtro) //LIMITAMOS LA FECHA PARA MOSTRAR SOLO LAS MAS RECIENTES
                            { continue; }

                            if (FiltroInicial2 == FiltroFinal2)
                            { FiltroInicial2 = Inicio; FiltroFinal2 = Final; }//Agregado el 12/12/2024 

                            if (((Inicio >= FiltroInicial2 && Final <= FiltroFinal2) ||
                                (Inicio <= FiltroInicial2 && Final >= FiltroFinal2)) && nej == 1)
                            {
                                if (Inicio <= FiltroInicial2 && Final >= FiltroFinal2)
                                {
                                    TimeSpan ajuste4 = FiltroInicial2 - Final; ajus3 = ajuste4.Days;
                                    FiltroFinal2 = Final; FiltroInicial2 = Inicio;
                                }
                                else { continue; }
                            } //Agregado el 12/12/2024 
                            nej = 1;
                            if (FiltroFinal2 == Final)
                            { TimeSpan ajuste1 = FiltroInicial2 - Inicio; ajus1 = ajuste1.Days; }//Agregado y actualizado el 12/12/2024

                            if (FiltroInicial2 == Inicio)
                            { TimeSpan ajuste2 = FiltroFinal2 - Final; ajus2 = ajuste2.Days; }//Agregado y actualizado el 12/12/2024 

                            if (Final > FiltroInicial2 && Final < FiltroFinal2)
                            { Final = FiltroInicial2; } //Agregado el 12/12/2024 

                            if (Final > filtro && Inicio < filtro)
                            {
                                Final = filtro;
                            }//Agregado el 18/12/2024 

                            writer1.WriteLine(Inicio.ToString("dd/MM/yyyy") + " , " + Final.ToString("dd/MM/yyyy"));
                            TimeSpan diferencia = Final - Inicio; int DiasDif = diferencia.Days;
                            DiasTotales = DiasTotales + DiasDif;
                            ajuste3 = ajus1 + ajus2; //Agregado el 12/12/2024 
                            FiltroFinal2 = Final; FiltroInicial2 = Inicio; //Agregado el 12/12/2024 
                        }

                        double SemanasTotales1 = 0; int ajustefinal = ajuste3;//Agregado el 12/12/2024  
                        if (ajuste3 > 1)
                        {
                            SemanasTotales1 = (DiasTotales - ajuste3 - ajus3) / 7.0;
                        }
                        else
                        {
                            SemanasTotales1 = (DiasTotales + ajuste3 + ajus3) / 7.0;
                        }
                        int SemanasTotales = (int)Math.Round(SemanasTotales1);
                        
                        if (File.Exists(RutaInfoCol8))
                        {
                            File.Delete(RutaInfoCol7);
                            File.Delete(RutaInfoCol8);
                        }
                        using (StreamWriter writer = new StreamWriter(RutaInfoCol6)) //Para que muestre las semanas totales finales en el forms
                        {
                            writer.WriteLine(SemanasTotales);
                        }

                        if (SemanasTotales >= 30)
                        {
                            //MessageBox.Show("Con Derecho " + SemanasTotales + "," + ajustefinal, "30 Semanas");
                            ValidadorDeDerecho = 1;
                        }
                        if (SemanasTotales < 30)
                        {
                            using (StreamReader reader2 = new StreamReader(RutaInfoCol3)) //52 semanas
                            {
                                string linea5; int SemanasTotales2, cinco = 1;

                                while ((linea5 = reader2.ReadLine()) != null)
                                {
                                    string[] COL2 = linea5.Split(',');
                                    string NUM3 = COL2[0].Trim();
                                    string NUM4 = COL2[1].Trim();

                                    DateTime Inicio2 = DateTime.Parse(NUM3);
                                    DateTime Final2 = DateTime.Parse(NUM4);
                                    DateTime filtro2 = Fecha_Inicio_Licencia;
                                    // Convierte las fechas de texto a DateTime
                                    DateTime inicio2 = DateTime.ParseExact(COL2[0].Trim(), "dd/MM/yyyy", CultureInfo.InvariantCulture);
                                    DateTime fin2 = DateTime.ParseExact(COL2[1].Trim(), "dd/MM/yyyy", CultureInfo.InvariantCulture);
                                    if ((inicio2 > filtro2.AddYears(-1) || inicio2 < filtro2) && fin2 < filtro2) //LIMITAMOS LA FECHA PARA MOSTRAR SOLO LAS MAS RECIENTES
                                    {
                                        if (inicio2 > filtro2.AddYears(-1) && fin2 > filtro2)
                                        {
                                            fin2 = filtro2;
                                        } //Agregado 17/12/2024
                                        else
                                        {
                                            continue;
                                        }
                                    }
                                    else
                                    {
                                        if (inicio2 > filtro2.AddYears(-1) && inicio2 < filtro2 && fin2 > filtro2)
                                        {
                                            fin2 = filtro2;
                                        } 
                                        else
                                        {
                                            continue;
                                        }
                                    }//Agregado 10/01/2025

                                    //if (Inicio2 < filtro2) { Inicio2 = filtro2; }
                                    if (Final2 > filtro2.AddYears(1)) { Final2 = FiltroInicial2.AddYears(1); }
                                    Final2 = fin2; Inicio2 = inicio2;

                                    //TimeSpan diferencia2 = Final2 - Inicio2;
                                    //int DiasDif2 = diferencia2.Days;
                                    //DiasTotales2 = DiasTotales2 + DiasDif2;
                                    cinco = 0;

                                    using (StreamWriter writer3 = new StreamWriter(RutaInfoCol7))
                                    {
                                        writer3.WriteLine(Inicio2.ToString("dd/MM/yyyy") + " , " + Final2.ToString("dd/MM/yyyy"));
                                        writer3.Close();
                                    }
                                    if (cinco == 0)
                                    {
                                        break;
                                    }
                                }
                                using (StreamReader reader3 = new StreamReader(RutaInfoCol7)) //52 semanas
                                {
                                    string linea6;

                                    while ((linea6 = reader3.ReadLine()) != null)
                                    {
                                        string[] COL3 = linea6.Split(',');
                                        string NUM5 = COL3[0].Trim();
                                        string NUM6 = COL3[1].Trim();

                                        DateTime FechaInicial52 = DateTime.Parse(NUM5);
                                        DateTime FechaFinal52 = DateTime.Parse(NUM6);

                                        TimeSpan diferencia2 = FechaFinal52 - FechaInicial52;
                                        int DiasDif2 = diferencia2.Days;
                                        DiasTotales2 = DiasTotales2 + DiasDif2;
                                    }
                                }
                                SemanasTotales2 = DiasTotales2 / 7;

                                using (StreamWriter writer4 = new StreamWriter(RutaInfoCol8)) //Para que muestre las semanas totales finales en el forms
                                {
                                    writer4.WriteLine(SemanasTotales2);
                                    writer4.Close();
                                }
                                if (SemanasTotales2 >= 52)
                                {
                                    //MessageBox.Show("Con Derecho total de semanas: " + SemanasTotales2, "52 Semanas");
                                    ValidadorDeDerecho = 1;
                                }
                                else
                                {
                                   // MessageBox.Show("Sin Derecho, Semanas Insuficientes: \n30 semanas: " + SemanasTotales +
                                     //   " semanas" + "\n" + "52 semanas: " + SemanasTotales2 + " semanas", "Semanas 52");
                                    ValidadorDeDerecho = 0;
                                }
                                reader2.Close();
                            }
                        }
                        reader.Close();
                    }
                }
                else //ES DEL VERIFICADOR DE SI TIENE O NO DERECHO
                {
                    MessageBox.Show("No procede", "Calculadora 140Bis");
                }
            }
            catch (Exception ex)
            {
                IntPtr hWnd3 = FindWindow(null, "Calculadora 140Bis");
                SetForegroundWindow(hWnd3);
                MessageBox.Show("Error: " + ex.Message, "Calculadora 140Bis");
            }
        }

        private static void Generar_Excel()
        {
            try
            {
                string ValidadorDeDerecho1;
                
                string NombreSemanas = "30Semanas"; // Aqui le asignamos el nombre que quiero que sea
                string rutaSemanasv = Path.Combine(Ruta_Carpeta, NombreSemanas + ExtensionTXT);

                string NombreSeFin = "5Final_30S"; // Aqui le asignamos el nombre que quiero que sea
                string rutaSemFinal = Path.Combine(Ruta_Carpeta, NombreSeFin + ExtensionTXT);

                string NombreSemanas52 = "52Semanas"; // Aqui le asignamos el nombre que quiero que sea
                string rutaSemanasv52 = Path.Combine(Ruta_Carpeta, NombreSemanas52 + ExtensionTXT);

                string NombreSeFin52 = "6Final_52S"; // Aqui le asignamos el nombre que quiero que sea
                string rutaSemFinal52 = Path.Combine(Ruta_Carpeta, NombreSeFin52 + ExtensionTXT);

                //Leer txt
                string[] lineas = File.ReadAllLines(Ruta_Final_NSS); //Info filtrada de sindo
                string[] lineas2 = File.ReadAllLines(rutaSemanasv);
                string lineas4;
                string[] lineas5 = File.ReadAllLines(RutaInfoCol1);
                //Crear una nueva aplicacion de excel
                Excel.Application excelApp = new Excel.Application();
                //Crear un nuevo libro de trabajo
                Excel.Workbook workbook = excelApp.Workbooks.Add();
                Excel.Sheets hojas = workbook.Sheets;
                //Obtener la primera hoja de calculo
                Excel.Worksheet worksheet1 = (Excel.Worksheet)workbook.Sheets[1];
                worksheet1.Name = "Resultado";

                worksheet1.Cells[2, 1] = "NSS";
                worksheet1.Cells[2, 2] = NSSCOMPLETO;

                if (ValidadorDeDerecho == 1)
                {
                    ValidadorDeDerecho1 = "Si";
                }
                else
                {
                    ValidadorDeDerecho1 = "No";
                }
                worksheet1.Cells[3, 1] = "Nombre: ";
                worksheet1.Cells[3, 2] = NOMBRE_ASEGURADO;

                worksheet1.Cells[4, 1] = "CURP ";
                worksheet1.Cells[4, 2] = CURP;

                worksheet1.Cells[5, 1] = "Tiene Derecho: ";
                worksheet1.Cells[5, 2] = ValidadorDeDerecho1;

                worksheet1.Cells[6, 1] = "Fecha Diagnostico: ";
                worksheet1.Cells[6, 2] = "'" + Texto_Fecha_Diagnostico;

                worksheet1.Cells[7, 1] = "Fecha Inicio Licencia: ";
                worksheet1.Cells[7, 2] = "'" + Texto_Fecha_Inicio_Licencia;

                worksheet1.Cells[8, 1] = "Fecha Solicitud: ";
                worksheet1.Cells[8, 2] = "'" + Texto_Fecha_Solicitud;

                if (SalarioFininalObtenido > 2593)
                {
                    worksheet1.Cells[9, 3] = "Salario TS: $2593";
                }
                worksheet1.Cells[9, 1] = "Salario Real: $";
                worksheet1.Cells[9, 2] = "'" + SalarioFininalObtenido;

                worksheet1.Cells[10, 1] = "Semanas validas 30 Sem: ";
                worksheet1.Cells[10, 2] = lineas2;

                if (File.Exists(rutaSemanasv52))
                {
                    string[] lineas6 = File.ReadAllLines(rutaSemanasv52);
                    worksheet1.Cells[10, 4] = "Semanas validas 52 Sem: ";
                    worksheet1.Cells[10, 5] = lineas6;
                }

                worksheet1.Cells[12, 1] = "Periodos Validos 30 Sem";
                worksheet1.Cells[13, 1] = "Inicio";
                worksheet1.Cells[13, 2] = "Final";
                //Procesar cada linea y escribir en excel
                int M = 14;
                using (StreamReader reader = new StreamReader(rutaSemFinal))
                {
                    while ((lineas4 = reader.ReadLine()) != null)
                    {
                        string[] columnas1 = lineas4.Split(','); //Separar por comas
                        string vari1 = columnas1[0].Trim();
                        string vari2 = columnas1[1].Trim();
                        worksheet1.Cells[M, 1] = "'" + vari1;
                        worksheet1.Cells[M, 2] = "'" + vari2;
                        M = M + 1;
                    }
                }

                if (File.Exists(rutaSemFinal52))
                {

                    string[] lineas7 = File.ReadAllLines(rutaSemFinal52);
                    worksheet1.Cells[12, 4] = "Periodos Validos 52 Sem";
                    worksheet1.Cells[13, 4] = "Inicio";
                    worksheet1.Cells[13, 5] = "Final";

                    string[] columnas2 = lineas7[0].Split(','); //Separar por comas
                    for (int j = 0; j < columnas2.Length; j++) //j mueve las columnas
                    {
                        worksheet1.Cells[14, j + 4] = "'" + columnas2[j];
                    }
                }

                worksheet1.Columns.AutoFit();//Para que las columnas se ajusten automaticamente

                hojas.Add(After: hojas[hojas.Count]);

                Excel.Worksheet worksheet2 = (Excel.Worksheet)workbook.Sheets[2];
                worksheet2.Name = "Info Sindo";

                //Escribir encabezados
                worksheet2.Cells[1, 1] = "RP";
                worksheet2.Cells[1, 2] = "Fecha Inicio";
                worksheet2.Cells[1, 3] = "Salario";
                worksheet2.Cells[1, 4] = "OF TF";
                worksheet2.Cells[1, 5] = "Fecha Final";

                //Procesar cada linea y escribir en excel
                for (int i = 0; i < lineas.Length; i++) //i mueve las filas
                {
                    string[] columnas3 = lineas[i].Split(','); //Separar por comas
                    for (int j = 0; j < columnas3.Length; j++) //j mueve las columnas
                    {
                        if (j == 0 || j == 1 || j == 4)
                        {
                            worksheet2.Cells[i + 2, j + 1] = "'" + columnas3[j];
                        }
                        else
                        {
                            worksheet2.Cells[i + 2, j + 1] = columnas3[j];
                        }
                    }
                }
                worksheet2.Columns.AutoFit();//Para que las columnas se ajusten automaticamente

                worksheet1.Activate();
                //Guardar el archivo
                workbook.SaveAs(Ruta_Guardar_Excel);

                //Cerrar y liberar recursos
                workbook.Close();
                excelApp.Quit();
                System.Runtime.InteropServices.Marshal.ReleaseComObject(workbook);
                System.Runtime.InteropServices.Marshal.ReleaseComObject(excelApp);

                MessageBox.Show("Excel Generado", "Excel");
            }
            catch (Exception ex)
            {
                IntPtr hWnd3 = FindWindow(null, "Calculadora 140Bis");
                SetForegroundWindow(hWnd3);
                MessageBox.Show("Error: " + ex.Message, "Calculadora 140Bis");
            }
        }

        private void textBox_FECHA_INICIO_LICENCIA_TextChanged(object sender, EventArgs e)
        {
            string texto = textBox_FECHA_INICIO_LICENCIA.Text.Replace("/", "");
            if (texto.Length > 2 && texto.Length <= 4)
            {
                texto = texto.Insert(2, "/");
            }
            else if (texto.Length > 4)
            {
                texto = texto.Insert(2, "/").Insert(5, "/");
            }
            textBox_FECHA_INICIO_LICENCIA.Text = texto;
            textBox_FECHA_INICIO_LICENCIA.SelectionStart = texto.Length;
        }

        private void textBox_FECHA_DIAGNOSTICO_TextChanged(object sender, EventArgs e)
        {
            string texto = textBox_FECHA_DIAGNOSTICO.Text.Replace("/", "");
            if (texto.Length > 2 && texto.Length <= 4)
            {
                texto = texto.Insert(2, "/");
            }
            else if (texto.Length > 4)
            {
                texto = texto.Insert(2, "/").Insert(5, "/");
            }
            textBox_FECHA_DIAGNOSTICO.Text = texto;
            textBox_FECHA_DIAGNOSTICO.SelectionStart = texto.Length;
        }

        private void textBox_FECHA_SOLICITUD_TextChanged(object sender, EventArgs e)
        {
            string texto = textBox_FECHA_SOLICITUD.Text.Replace("/", "");
            if (texto.Length > 2 && texto.Length <= 4)
            {
                texto = texto.Insert(2, "/");
            }
            else if (texto.Length > 4)
            {
                texto = texto.Insert(2, "/").Insert(5, "/");
            }
            textBox_FECHA_SOLICITUD.Text = texto;
            textBox_FECHA_SOLICITUD.SelectionStart = texto.Length;
        }

        private void button_Generar_ExcelNSS_Click(object sender, EventArgs e)
        {
            Generar_Excel();
            if (File.Exists(Ruta_Guardar_NSS)) { File.Delete(Ruta_Guardar_NSS); }
            if (File.Exists(Ruta_NSS_Limpiado)) { File.Delete(Ruta_NSS_Limpiado); }
            if (File.Exists(Ruta_Final_NSS)) { File.Delete(Ruta_Final_NSS); }
            if (File.Exists(RutaInfoCol1)) { File.Delete(RutaInfoCol1); }
            if (File.Exists(RutaInfoCol2)) { File.Delete(RutaInfoCol2); }
            if (File.Exists(RutaInfoCol3)) { File.Delete(RutaInfoCol3); }
            if (File.Exists(RutaInfoCol4)) { File.Delete(RutaInfoCol4); }
            if (File.Exists(RutaInfoCol5)) { File.Delete(RutaInfoCol5); }
            if (File.Exists(RutaInfoCol6)) { File.Delete(RutaInfoCol6); }
            if (File.Exists(RutaInfoCol7)) { File.Delete(RutaInfoCol7); }
            if (File.Exists(RutaInfoCol8)) { File.Delete(RutaInfoCol8); }
            if (File.Exists(Sem30_1)) { File.Delete(Sem30_1); }
            if (File.Exists(Salariotxt1Global)) { File.Delete(Salariotxt1Global); }
            if (File.Exists(Salariotxt2Global)) { File.Delete(Salariotxt2Global); }
        }

        private void button_LIMPIAR_CAMPOS_Click(object sender, EventArgs e)
        {
            string nada = ("");
            textBox_FECHA_DIAGNOSTICO.Text = nada;
            textBox_FECHA_INICIO_LICENCIA.Text = nada;
            textBox_FECHA_SOLICITUD.Text = nada;
            textBox_NSS.Text = nada;
        }

        private void carpetaToolStripMenuItem_Click(object sender, EventArgs e)
        {
            DialogResult resultado = MessageBox.Show("¿Desea cambiar la carpeta?", "Carpeta txt", MessageBoxButtons.YesNo, MessageBoxIcon.Question);
            if (resultado == DialogResult.Yes)
            {
                SeleccionarCarpeta();
            }
        }

        private void Form1_FormClosing(object sender, FormClosingEventArgs e)
        {
            if (File.Exists(Ruta_Guardar_NSS)) { File.Delete(Ruta_Guardar_NSS); }
            if (File.Exists(Ruta_NSS_Limpiado)) { File.Delete(Ruta_NSS_Limpiado); }
            if (File.Exists(Ruta_Final_NSS)) { File.Delete(Ruta_Final_NSS); }
            if (File.Exists(RutaInfoCol1)) { File.Delete(RutaInfoCol1); }
            if (File.Exists(RutaInfoCol2)) { File.Delete(RutaInfoCol2); }
            if (File.Exists(RutaInfoCol3)) { File.Delete(RutaInfoCol3); }
            if (File.Exists(RutaInfoCol4)) { File.Delete(RutaInfoCol4); }
            if (File.Exists(RutaInfoCol5)) { File.Delete(RutaInfoCol5); }
            if (File.Exists(RutaInfoCol6)) { File.Delete(RutaInfoCol6); }
            if (File.Exists(RutaInfoCol7)) { File.Delete(RutaInfoCol7); }
            if (File.Exists(RutaInfoCol8)) { File.Delete(RutaInfoCol8); }
            if (File.Exists(Sem30_1)) { File.Delete(Sem30_1); }
            if (File.Exists(Salariotxt1Global)) { File.Delete(Salariotxt1Global); }
            if (File.Exists(Salariotxt2Global)) { File.Delete(Salariotxt2Global); }
        }
    }
}
