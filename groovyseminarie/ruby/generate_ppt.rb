require 'win32ole'

ppt = WIN32OLE.new('Powerpoint.Application')
ppt.Visible = true
@pre = ppt.presentations.Open("C:/programmering/workspace/Groovyseminarie/dokument/groovyseminarium_source.pptx")


def replaceCodeAnchors
  @pre.Slides.each{|slide|
  slide.Shapes.each{|shape|
    replace( shape, slide ) if shape.TextFrame.TextRange.Text =~ /\[.*\]/ 
  }
}
end

def replace(ole_shape, ole_slide) 
  fileName = ole_shape.TextFrame.TextRange.Text
  fileName["["]=""
  fileName["]"]=""
  fileName += ".groovy"
  fileUrl = "C:/programmering/workspace/groovyseminarie/src/se/britech/groovySeminarie/groovy/#{fileName}"
  text = "#{fileName}\n-------------------------------------------\n"
  File.open(fileUrl, "r").each{|l| text += l }
  
  # Sätt texten
  textRange = ole_shape.TextFrame.TextRange
  font = textRange.Font
  textRange.Text = text
  font.Name = "Courier NEW";
  font.Size = 15;
#  p font.Color.ole_methods
#  p font.ole_methods
  
  textRange.ParagraphFormat.Alignment = 1 # left
  
  p ole_shape.TextFrame.TextRange.Font.ole_methods
  
  
  # Skapa en länk
  onClick = 1
  link = 7
  textRange.ActionSettings.Item(onClick).Action = link
  textRange.ActionSettings.Item(onClick).Hyperlink.Address = "C:/programmering/workspace/groovyseminarie/ruby/run_groovy_script.rb"

end

replaceCodeAnchors

#  Run
@pre.SlideShowSettings.Run

# close powerpoint, will close all the currently open files
ppt.Quit()