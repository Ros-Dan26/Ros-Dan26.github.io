namespace Calculadora_140BISV2
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Form1));
            this.menuStrip1 = new System.Windows.Forms.MenuStrip();
            this.carpetaToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.tabControl1 = new System.Windows.Forms.TabControl();
            this.tabPage1 = new System.Windows.Forms.TabPage();
            this.label12 = new System.Windows.Forms.Label();
            this.label11 = new System.Windows.Forms.Label();
            this.button_LIMPIAR_CAMPOS = new System.Windows.Forms.Button();
            this.button_INICIO_PROGRAMA = new System.Windows.Forms.Button();
            this.label6 = new System.Windows.Forms.Label();
            this.label5 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.textBox_CONTRASEÑA_SINDO = new System.Windows.Forms.TextBox();
            this.textBox_USUARIO_SINDO = new System.Windows.Forms.TextBox();
            this.textBox_FECHA_SOLICITUD = new System.Windows.Forms.TextBox();
            this.textBox_FECHA_DIAGNOSTICO = new System.Windows.Forms.TextBox();
            this.textBox_FECHA_INICIO_LICENCIA = new System.Windows.Forms.TextBox();
            this.textBox_NSS = new System.Windows.Forms.TextBox();
            this.tabPage2 = new System.Windows.Forms.TabPage();
            this.button_Generar_ExcelNSS = new System.Windows.Forms.Button();
            this.label9 = new System.Windows.Forms.Label();
            this.label10 = new System.Windows.Forms.Label();
            this.label8 = new System.Windows.Forms.Label();
            this.label7 = new System.Windows.Forms.Label();
            this.tableLayoutPanel2 = new System.Windows.Forms.TableLayoutPanel();
            this.tableLayoutPanel1 = new System.Windows.Forms.TableLayoutPanel();
            this.label_Semanas_Validas_52S = new System.Windows.Forms.Label();
            this.label_Semanas_Validas_30S = new System.Windows.Forms.Label();
            this.label_Salario = new System.Windows.Forms.Label();
            this.label_Derecho = new System.Windows.Forms.Label();
            this.label_Fecha_Diagnostico = new System.Windows.Forms.Label();
            this.label_Fecha_Inicio_Licencia = new System.Windows.Forms.Label();
            this.label_Fecha_Solicitud = new System.Windows.Forms.Label();
            this.label_NSS = new System.Windows.Forms.Label();
            this.label_CURP = new System.Windows.Forms.Label();
            this.label_Nombre = new System.Windows.Forms.Label();
            this.menuStrip1.SuspendLayout();
            this.tabControl1.SuspendLayout();
            this.tabPage1.SuspendLayout();
            this.tabPage2.SuspendLayout();
            this.SuspendLayout();
            // 
            // menuStrip1
            // 
            this.menuStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.carpetaToolStripMenuItem});
            this.menuStrip1.Location = new System.Drawing.Point(0, 0);
            this.menuStrip1.Name = "menuStrip1";
            this.menuStrip1.Size = new System.Drawing.Size(488, 24);
            this.menuStrip1.TabIndex = 1;
            this.menuStrip1.Text = "menuStrip1";
            // 
            // carpetaToolStripMenuItem
            // 
            this.carpetaToolStripMenuItem.Name = "carpetaToolStripMenuItem";
            this.carpetaToolStripMenuItem.Size = new System.Drawing.Size(60, 20);
            this.carpetaToolStripMenuItem.Text = "Carpeta";
            this.carpetaToolStripMenuItem.Click += new System.EventHandler(this.carpetaToolStripMenuItem_Click);
            // 
            // tabControl1
            // 
            this.tabControl1.Controls.Add(this.tabPage1);
            this.tabControl1.Controls.Add(this.tabPage2);
            this.tabControl1.Location = new System.Drawing.Point(0, 27);
            this.tabControl1.Name = "tabControl1";
            this.tabControl1.SelectedIndex = 0;
            this.tabControl1.Size = new System.Drawing.Size(497, 394);
            this.tabControl1.TabIndex = 2;
            // 
            // tabPage1
            // 
            this.tabPage1.Controls.Add(this.label12);
            this.tabPage1.Controls.Add(this.label11);
            this.tabPage1.Controls.Add(this.button_LIMPIAR_CAMPOS);
            this.tabPage1.Controls.Add(this.button_INICIO_PROGRAMA);
            this.tabPage1.Controls.Add(this.label6);
            this.tabPage1.Controls.Add(this.label5);
            this.tabPage1.Controls.Add(this.label4);
            this.tabPage1.Controls.Add(this.label3);
            this.tabPage1.Controls.Add(this.label2);
            this.tabPage1.Controls.Add(this.label1);
            this.tabPage1.Controls.Add(this.textBox_CONTRASEÑA_SINDO);
            this.tabPage1.Controls.Add(this.textBox_USUARIO_SINDO);
            this.tabPage1.Controls.Add(this.textBox_FECHA_SOLICITUD);
            this.tabPage1.Controls.Add(this.textBox_FECHA_DIAGNOSTICO);
            this.tabPage1.Controls.Add(this.textBox_FECHA_INICIO_LICENCIA);
            this.tabPage1.Controls.Add(this.textBox_NSS);
            this.tabPage1.Location = new System.Drawing.Point(4, 22);
            this.tabPage1.Name = "tabPage1";
            this.tabPage1.Padding = new System.Windows.Forms.Padding(3);
            this.tabPage1.Size = new System.Drawing.Size(489, 368);
            this.tabPage1.TabIndex = 0;
            this.tabPage1.Text = "Captura";
            this.tabPage1.UseVisualStyleBackColor = true;
            // 
            // label12
            // 
            this.label12.AutoSize = true;
            this.label12.Font = new System.Drawing.Font("Times New Roman", 20.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label12.Location = new System.Drawing.Point(119, 13);
            this.label12.Name = "label12";
            this.label12.Size = new System.Drawing.Size(231, 31);
            this.label12.TabIndex = 15;
            this.label12.Text = "Calculadora 140Bis";
            // 
            // label11
            // 
            this.label11.AutoSize = true;
            this.label11.Font = new System.Drawing.Font("Times New Roman", 15.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label11.Location = new System.Drawing.Point(22, 192);
            this.label11.Name = "label11";
            this.label11.Size = new System.Drawing.Size(152, 23);
            this.label11.TabIndex = 14;
            this.label11.Text = "Ingreso a SINDO";
            // 
            // button_LIMPIAR_CAMPOS
            // 
            this.button_LIMPIAR_CAMPOS.Location = new System.Drawing.Point(145, 283);
            this.button_LIMPIAR_CAMPOS.Name = "button_LIMPIAR_CAMPOS";
            this.button_LIMPIAR_CAMPOS.Size = new System.Drawing.Size(75, 23);
            this.button_LIMPIAR_CAMPOS.TabIndex = 13;
            this.button_LIMPIAR_CAMPOS.Text = "LIMPIAR";
            this.button_LIMPIAR_CAMPOS.UseVisualStyleBackColor = true;
            this.button_LIMPIAR_CAMPOS.Click += new System.EventHandler(this.button_LIMPIAR_CAMPOS_Click);
            // 
            // button_INICIO_PROGRAMA
            // 
            this.button_INICIO_PROGRAMA.Location = new System.Drawing.Point(20, 283);
            this.button_INICIO_PROGRAMA.Name = "button_INICIO_PROGRAMA";
            this.button_INICIO_PROGRAMA.Size = new System.Drawing.Size(75, 23);
            this.button_INICIO_PROGRAMA.TabIndex = 12;
            this.button_INICIO_PROGRAMA.Text = "VALIDAR";
            this.button_INICIO_PROGRAMA.UseVisualStyleBackColor = true;
            this.button_INICIO_PROGRAMA.Click += new System.EventHandler(this.button_INICIO_PROGRAMA_Click);
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Font = new System.Drawing.Font("Times New Roman", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label6.Location = new System.Drawing.Point(257, 223);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(68, 15);
            this.label6.TabIndex = 11;
            this.label6.Text = "Contraseña";
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Font = new System.Drawing.Font("Times New Roman", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label5.Location = new System.Drawing.Point(17, 223);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(49, 15);
            this.label5.TabIndex = 10;
            this.label5.Text = "Usuario";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Font = new System.Drawing.Font("Times New Roman", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label4.Location = new System.Drawing.Point(257, 124);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(105, 15);
            this.label4.TabIndex = 9;
            this.label4.Text = "Fecha de Solicitud";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Font = new System.Drawing.Font("Times New Roman", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label3.Location = new System.Drawing.Point(17, 124);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(123, 15);
            this.label3.TabIndex = 8;
            this.label3.Text = "Fecha de Diagnostico";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Font = new System.Drawing.Font("Times New Roman", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label2.Location = new System.Drawing.Point(257, 65);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(151, 15);
            this.label2.TabIndex = 7;
            this.label2.Text = "Fecha de Inicio de Licencia";
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("Times New Roman", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label1.Location = new System.Drawing.Point(23, 66);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(30, 15);
            this.label1.TabIndex = 6;
            this.label1.Text = "NSS";
            // 
            // textBox_CONTRASEÑA_SINDO
            // 
            this.textBox_CONTRASEÑA_SINDO.Location = new System.Drawing.Point(260, 241);
            this.textBox_CONTRASEÑA_SINDO.Name = "textBox_CONTRASEÑA_SINDO";
            this.textBox_CONTRASEÑA_SINDO.Size = new System.Drawing.Size(200, 20);
            this.textBox_CONTRASEÑA_SINDO.TabIndex = 5;
            // 
            // textBox_USUARIO_SINDO
            // 
            this.textBox_USUARIO_SINDO.Location = new System.Drawing.Point(20, 241);
            this.textBox_USUARIO_SINDO.Name = "textBox_USUARIO_SINDO";
            this.textBox_USUARIO_SINDO.Size = new System.Drawing.Size(200, 20);
            this.textBox_USUARIO_SINDO.TabIndex = 4;
            // 
            // textBox_FECHA_SOLICITUD
            // 
            this.textBox_FECHA_SOLICITUD.Location = new System.Drawing.Point(260, 142);
            this.textBox_FECHA_SOLICITUD.MaxLength = 10;
            this.textBox_FECHA_SOLICITUD.Name = "textBox_FECHA_SOLICITUD";
            this.textBox_FECHA_SOLICITUD.Size = new System.Drawing.Size(200, 20);
            this.textBox_FECHA_SOLICITUD.TabIndex = 3;
            this.textBox_FECHA_SOLICITUD.TextChanged += new System.EventHandler(this.textBox_FECHA_SOLICITUD_TextChanged);
            // 
            // textBox_FECHA_DIAGNOSTICO
            // 
            this.textBox_FECHA_DIAGNOSTICO.Location = new System.Drawing.Point(20, 142);
            this.textBox_FECHA_DIAGNOSTICO.MaxLength = 10;
            this.textBox_FECHA_DIAGNOSTICO.Name = "textBox_FECHA_DIAGNOSTICO";
            this.textBox_FECHA_DIAGNOSTICO.Size = new System.Drawing.Size(200, 20);
            this.textBox_FECHA_DIAGNOSTICO.TabIndex = 2;
            this.textBox_FECHA_DIAGNOSTICO.TextChanged += new System.EventHandler(this.textBox_FECHA_DIAGNOSTICO_TextChanged);
            // 
            // textBox_FECHA_INICIO_LICENCIA
            // 
            this.textBox_FECHA_INICIO_LICENCIA.Location = new System.Drawing.Point(260, 83);
            this.textBox_FECHA_INICIO_LICENCIA.MaxLength = 10;
            this.textBox_FECHA_INICIO_LICENCIA.Name = "textBox_FECHA_INICIO_LICENCIA";
            this.textBox_FECHA_INICIO_LICENCIA.Size = new System.Drawing.Size(200, 20);
            this.textBox_FECHA_INICIO_LICENCIA.TabIndex = 1;
            this.textBox_FECHA_INICIO_LICENCIA.TextChanged += new System.EventHandler(this.textBox_FECHA_INICIO_LICENCIA_TextChanged);
            // 
            // textBox_NSS
            // 
            this.textBox_NSS.Location = new System.Drawing.Point(20, 83);
            this.textBox_NSS.MaxLength = 11;
            this.textBox_NSS.Name = "textBox_NSS";
            this.textBox_NSS.Size = new System.Drawing.Size(200, 20);
            this.textBox_NSS.TabIndex = 0;
            // 
            // tabPage2
            // 
            this.tabPage2.Controls.Add(this.button_Generar_ExcelNSS);
            this.tabPage2.Controls.Add(this.label9);
            this.tabPage2.Controls.Add(this.label10);
            this.tabPage2.Controls.Add(this.label8);
            this.tabPage2.Controls.Add(this.label7);
            this.tabPage2.Controls.Add(this.tableLayoutPanel2);
            this.tabPage2.Controls.Add(this.tableLayoutPanel1);
            this.tabPage2.Controls.Add(this.label_Semanas_Validas_52S);
            this.tabPage2.Controls.Add(this.label_Semanas_Validas_30S);
            this.tabPage2.Controls.Add(this.label_Salario);
            this.tabPage2.Controls.Add(this.label_Derecho);
            this.tabPage2.Controls.Add(this.label_Fecha_Diagnostico);
            this.tabPage2.Controls.Add(this.label_Fecha_Inicio_Licencia);
            this.tabPage2.Controls.Add(this.label_Fecha_Solicitud);
            this.tabPage2.Controls.Add(this.label_NSS);
            this.tabPage2.Controls.Add(this.label_CURP);
            this.tabPage2.Controls.Add(this.label_Nombre);
            this.tabPage2.Location = new System.Drawing.Point(4, 22);
            this.tabPage2.Name = "tabPage2";
            this.tabPage2.Padding = new System.Windows.Forms.Padding(3);
            this.tabPage2.Size = new System.Drawing.Size(489, 368);
            this.tabPage2.TabIndex = 1;
            this.tabPage2.Text = "Resultado";
            this.tabPage2.UseVisualStyleBackColor = true;
            // 
            // button_Generar_ExcelNSS
            // 
            this.button_Generar_ExcelNSS.Location = new System.Drawing.Point(419, 332);
            this.button_Generar_ExcelNSS.Name = "button_Generar_ExcelNSS";
            this.button_Generar_ExcelNSS.Size = new System.Drawing.Size(53, 23);
            this.button_Generar_ExcelNSS.TabIndex = 16;
            this.button_Generar_ExcelNSS.Text = "Excel";
            this.button_Generar_ExcelNSS.UseVisualStyleBackColor = true;
            this.button_Generar_ExcelNSS.Click += new System.EventHandler(this.button_Generar_ExcelNSS_Click);
            // 
            // label9
            // 
            this.label9.AutoSize = true;
            this.label9.Location = new System.Drawing.Point(355, 204);
            this.label9.Name = "label9";
            this.label9.Size = new System.Drawing.Size(21, 13);
            this.label9.TabIndex = 15;
            this.label9.Text = "Fin";
            // 
            // label10
            // 
            this.label10.AutoSize = true;
            this.label10.Location = new System.Drawing.Point(275, 204);
            this.label10.Name = "label10";
            this.label10.Size = new System.Drawing.Size(32, 13);
            this.label10.TabIndex = 14;
            this.label10.Text = "Inicio";
            // 
            // label8
            // 
            this.label8.AutoSize = true;
            this.label8.Location = new System.Drawing.Point(124, 204);
            this.label8.Name = "label8";
            this.label8.Size = new System.Drawing.Size(21, 13);
            this.label8.TabIndex = 13;
            this.label8.Text = "Fin";
            // 
            // label7
            // 
            this.label7.AutoSize = true;
            this.label7.Location = new System.Drawing.Point(44, 204);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(32, 13);
            this.label7.TabIndex = 12;
            this.label7.Text = "Inicio";
            // 
            // tableLayoutPanel2
            // 
            this.tableLayoutPanel2.ColumnCount = 2;
            this.tableLayoutPanel2.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel2.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel2.Location = new System.Drawing.Point(250, 220);
            this.tableLayoutPanel2.Name = "tableLayoutPanel2";
            this.tableLayoutPanel2.RowCount = 2;
            this.tableLayoutPanel2.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel2.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel2.Size = new System.Drawing.Size(153, 135);
            this.tableLayoutPanel2.TabIndex = 11;
            // 
            // tableLayoutPanel1
            // 
            this.tableLayoutPanel1.ColumnCount = 2;
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel1.Location = new System.Drawing.Point(23, 220);
            this.tableLayoutPanel1.Name = "tableLayoutPanel1";
            this.tableLayoutPanel1.RowCount = 2;
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel1.Size = new System.Drawing.Size(153, 135);
            this.tableLayoutPanel1.TabIndex = 10;
            // 
            // label_Semanas_Validas_52S
            // 
            this.label_Semanas_Validas_52S.AutoSize = true;
            this.label_Semanas_Validas_52S.Location = new System.Drawing.Point(247, 171);
            this.label_Semanas_Validas_52S.Name = "label_Semanas_Validas_52S";
            this.label_Semanas_Validas_52S.Size = new System.Drawing.Size(116, 13);
            this.label_Semanas_Validas_52S.TabIndex = 9;
            this.label_Semanas_Validas_52S.Text = "Semanas_Validas_52S";
            // 
            // label_Semanas_Validas_30S
            // 
            this.label_Semanas_Validas_30S.AutoSize = true;
            this.label_Semanas_Validas_30S.Location = new System.Drawing.Point(20, 171);
            this.label_Semanas_Validas_30S.Name = "label_Semanas_Validas_30S";
            this.label_Semanas_Validas_30S.Size = new System.Drawing.Size(116, 13);
            this.label_Semanas_Validas_30S.TabIndex = 8;
            this.label_Semanas_Validas_30S.Text = "Semanas_Validas_30S";
            // 
            // label_Salario
            // 
            this.label_Salario.AutoSize = true;
            this.label_Salario.Location = new System.Drawing.Point(247, 135);
            this.label_Salario.Name = "label_Salario";
            this.label_Salario.Size = new System.Drawing.Size(39, 13);
            this.label_Salario.TabIndex = 7;
            this.label_Salario.Text = "Salario";
            // 
            // label_Derecho
            // 
            this.label_Derecho.AutoSize = true;
            this.label_Derecho.Location = new System.Drawing.Point(20, 135);
            this.label_Derecho.Name = "label_Derecho";
            this.label_Derecho.Size = new System.Drawing.Size(48, 13);
            this.label_Derecho.TabIndex = 6;
            this.label_Derecho.Text = "Derecho";
            // 
            // label_Fecha_Diagnostico
            // 
            this.label_Fecha_Diagnostico.AutoSize = true;
            this.label_Fecha_Diagnostico.Location = new System.Drawing.Point(247, 95);
            this.label_Fecha_Diagnostico.Name = "label_Fecha_Diagnostico";
            this.label_Fecha_Diagnostico.Size = new System.Drawing.Size(113, 13);
            this.label_Fecha_Diagnostico.TabIndex = 5;
            this.label_Fecha_Diagnostico.Text = "Fecha De Diagnostico";
            // 
            // label_Fecha_Inicio_Licencia
            // 
            this.label_Fecha_Inicio_Licencia.AutoSize = true;
            this.label_Fecha_Inicio_Licencia.Location = new System.Drawing.Point(20, 95);
            this.label_Fecha_Inicio_Licencia.Name = "label_Fecha_Inicio_Licencia";
            this.label_Fecha_Inicio_Licencia.Size = new System.Drawing.Size(125, 13);
            this.label_Fecha_Inicio_Licencia.TabIndex = 4;
            this.label_Fecha_Inicio_Licencia.Text = "Fecha De Inicio Licencia";
            // 
            // label_Fecha_Solicitud
            // 
            this.label_Fecha_Solicitud.AutoSize = true;
            this.label_Fecha_Solicitud.Location = new System.Drawing.Point(247, 61);
            this.label_Fecha_Solicitud.Name = "label_Fecha_Solicitud";
            this.label_Fecha_Solicitud.Size = new System.Drawing.Size(97, 13);
            this.label_Fecha_Solicitud.TabIndex = 3;
            this.label_Fecha_Solicitud.Text = "Fecha De Solicitud";
            // 
            // label_NSS
            // 
            this.label_NSS.AutoSize = true;
            this.label_NSS.Location = new System.Drawing.Point(20, 61);
            this.label_NSS.Name = "label_NSS";
            this.label_NSS.Size = new System.Drawing.Size(29, 13);
            this.label_NSS.TabIndex = 2;
            this.label_NSS.Text = "NSS";
            // 
            // label_CURP
            // 
            this.label_CURP.AutoSize = true;
            this.label_CURP.Location = new System.Drawing.Point(20, 35);
            this.label_CURP.Name = "label_CURP";
            this.label_CURP.Size = new System.Drawing.Size(37, 13);
            this.label_CURP.TabIndex = 1;
            this.label_CURP.Text = "CURP";
            // 
            // label_Nombre
            // 
            this.label_Nombre.AutoSize = true;
            this.label_Nombre.Location = new System.Drawing.Point(20, 12);
            this.label_Nombre.Name = "label_Nombre";
            this.label_Nombre.Size = new System.Drawing.Size(44, 13);
            this.label_Nombre.TabIndex = 0;
            this.label_Nombre.Text = "Nombre";
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(488, 416);
            this.Controls.Add(this.tabControl1);
            this.Controls.Add(this.menuStrip1);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.MainMenuStrip = this.menuStrip1;
            this.Name = "Form1";
            this.Text = "Calculadora 140Bis";
            this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.Form1_FormClosing);
            this.menuStrip1.ResumeLayout(false);
            this.menuStrip1.PerformLayout();
            this.tabControl1.ResumeLayout(false);
            this.tabPage1.ResumeLayout(false);
            this.tabPage1.PerformLayout();
            this.tabPage2.ResumeLayout(false);
            this.tabPage2.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.MenuStrip menuStrip1;
        private System.Windows.Forms.ToolStripMenuItem carpetaToolStripMenuItem;
        private System.Windows.Forms.TabControl tabControl1;
        private System.Windows.Forms.TabPage tabPage1;
        private System.Windows.Forms.TabPage tabPage2;
        private System.Windows.Forms.Button button_LIMPIAR_CAMPOS;
        private System.Windows.Forms.Button button_INICIO_PROGRAMA;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.TextBox textBox_CONTRASEÑA_SINDO;
        private System.Windows.Forms.TextBox textBox_USUARIO_SINDO;
        private System.Windows.Forms.TextBox textBox_FECHA_SOLICITUD;
        private System.Windows.Forms.TextBox textBox_FECHA_DIAGNOSTICO;
        private System.Windows.Forms.TextBox textBox_FECHA_INICIO_LICENCIA;
        private System.Windows.Forms.TextBox textBox_NSS;
        private System.Windows.Forms.Label label9;
        private System.Windows.Forms.Label label10;
        private System.Windows.Forms.Label label8;
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.TableLayoutPanel tableLayoutPanel2;
        private System.Windows.Forms.TableLayoutPanel tableLayoutPanel1;
        private System.Windows.Forms.Label label_Semanas_Validas_52S;
        private System.Windows.Forms.Label label_Semanas_Validas_30S;
        private System.Windows.Forms.Label label_Salario;
        private System.Windows.Forms.Label label_Derecho;
        private System.Windows.Forms.Label label_Fecha_Diagnostico;
        private System.Windows.Forms.Label label_Fecha_Inicio_Licencia;
        private System.Windows.Forms.Label label_Fecha_Solicitud;
        private System.Windows.Forms.Label label_NSS;
        private System.Windows.Forms.Label label_CURP;
        private System.Windows.Forms.Label label_Nombre;
        private System.Windows.Forms.Button button_Generar_ExcelNSS;
        private System.Windows.Forms.Label label12;
        private System.Windows.Forms.Label label11;
    }
}

