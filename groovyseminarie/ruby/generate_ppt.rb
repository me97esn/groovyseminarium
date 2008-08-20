require 'win32ole'

ppt = WIN32OLE.new('Powerpoint.Application')
ppt.Visible = true
@pre = ppt.presentations.Open("C:/programmering/workspace/Groovyseminarie/dokument/groovyseminarium_source.pptx")


def replaceCodeAnchors
  @pre.Slides.each{|slide|
  slide.Shapes.each{|shape|
    replace( shape ) if shape.TextFrame.TextRange.Text =~ /\[.*\]/ 
  }
}
end

def replace(ole_shape) 
  fileName = ole_shape.TextFrame.TextRange.Text
  fileName["["]=""
  fileName["]"]=""
  fileName += ".groovy"
  fileUrl = "C:/programmering/workspace/groovyseminarie/src/se/britech/groovySeminarie/groovy/#{fileName}"
  text = "#{fileName}\n-------------------------------------------\n"
  File.open(fileUrl, "r").each{|l| text += l }
  
  # Sätt texten
  ole_shape.TextFrame.TextRange.Text = text
  ole_shape.TextFrame.TextRange.Font.Name = "Courier NEW";
  ole_shape.TextFrame.TextRange.Font.Size = 15;
  ole_shape.TextFrame.TextRange.ParagraphFormat.Alignment = 1 # left
  
  # Skapa en länk
#  p ole_shape.TextFrame.TextRange.Item(MouseClick)

  onClick = 1
  ole_shape.TextFrame.TextRange.ActionSettings.Item(onClick).Action = 9
  ole_shape.TextFrame.TextRange.ActionSettings.Item(onClick).Hyperlink.Address = "C:/programmering/workspace/groovyseminarie/ruby/run_groovy_script.rb"
end

replaceCodeAnchors

#  Run
@pre.SlideShowSettings.Run

# close powerpoint, will close all the currently open files
#ppt.Quit()