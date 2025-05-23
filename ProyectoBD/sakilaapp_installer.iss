[Setup]
AppName=SakilaApp
AppVersion=1.0
DefaultDirName={commonpf}\SakilaApp
DefaultGroupName=SakilaApp
OutputBaseFilename=SakilaAppInstaller
Compression=lzma
SolidCompression=yes
DisableProgramGroupPage=yes
WizardStyle=modern
AppPublisher=Miguel Angel Pacheco Arteaga

[Files]
Source: "SakilaApp.exe"; DestDir: "{app}"; Flags: ignoreversion
Source: "ProgramacionVisual.jar"; DestDir: "{app}"; Flags: ignoreversion
Source: "Sakila.ico"; DestDir: "{app}"; Flags: ignoreversion
Source: "resources\*"; DestDir: "{app}\resources"; Flags: ignoreversion recursesubdirs createallsubdirs
Source: "config\*"; DestDir: "{app}\config"; Flags: ignoreversion recursesubdirs createallsubdirs
Source: "jre\*"; DestDir: "{app}\jre"; Flags: ignoreversion recursesubdirs createallsubdirs

[Icons]
Name: "{group}\SakilaApp"; Filename: "{app}\SakilaApp.exe"; WorkingDir: "{app}"
Name: "{commondesktop}\SakilaApp"; Filename: "{app}\SakilaApp.exe"; Tasks: desktopicon; WorkingDir: "{app}"

[Tasks]
Name: "desktopicon"; Description: "Crear un ícono en el escritorio"; GroupDescription: "Opciones adicionales:"

[Run]
Filename: "{app}\SakilaApp.exe"; Description: "Ejecutar SakilaApp"; Flags: nowait postinstall skipifsilent
