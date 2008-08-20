require 'win32ole'

ppt = WIN32OLE.new('Powerpoint.Application')
ppt.Visible = true
pre = ppt.Presentations.Open("C:\\programmering\\workspace\\Groovyseminarie\\dokument\\groovyseminarium_source.pptx")

pre.Slides.each{|slide|
  slide.Shapes.each{|shape|
    p shape.TextFrame.TextRange.Text 
  }
}

#  Run
pre.SlideShowSettings.Run

# close powerpoint, will close all the currently open files
ppt.Quit()
