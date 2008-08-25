require 'win32ole'
class FileInIde
  def FileInIde.open(name)
    a = WIN32OLE.new("AutoItX3.Control")
    a.WinActivate "Java"
    a.Send "^+r"
    a.Send name
    a.Send "{Enter}"
  end
end