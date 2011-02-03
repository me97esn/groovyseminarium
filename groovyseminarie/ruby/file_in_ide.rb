require 'win32ole'
class FileInIde
  def FileInIde.open(name)
    a = WIN32OLE.new("AutoItX3.Control")
    a.WinActivate "groovyseminarie"
    a.Send "^+n"
    a.Send name
    sleep 0.2
    a.Send "{Enter}"
  end
end