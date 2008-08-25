require 'win32ole'
require 'watir'

a = WIN32OLE.new("AutoItX3.Control")
p a.ole_methods
a.WinMinimizeAll
#p a.WinActive
p a.WinGetProcess("eclipse").class
a.WinActivate "Java"
#p a.SW_SHOW a 
#a.ControlFocus("eclipse", "File", "")
proc = a.WinGetProcess("taskmgr")
p proc.methods

