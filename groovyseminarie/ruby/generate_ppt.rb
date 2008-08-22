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
#  fileName += ".groovy"
  fileUrl = ""
  if fileName =~ /\.groovy/
    fileUrl = "C:/programmering/workspace/groovyseminarie/src/se/britech/groovySeminarie/groovy/#{fileName}"
  else
    fileUrl = "C:/programmering/workspace/groovyseminarie/src/se/britech/groovySeminarie/java/#{fileName}"
  end
  
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
  
  # Skapa en länk
  onClick = 1
  link = 7
  ole_shape.ActionSettings.Item(onClick).Action = link
  rubyScriptName = "run_#{fileName}_script.rb"
  adress = "C:/programmering/workspace/groovyseminarie/ruby/generated/#{rubyScriptName}"
  ole_shape.ActionSettings.Item(onClick).Hyperlink.Address = adress
  
  generate_ruby_script fileUrl, adress
end

def generate_ruby_script(groovyFile, rubyFile)
  p rubyFile
  f = File.new(rubyFile, "w+")
  f.puts "groovy #{groovyFile}"
  f.close
end

replaceCodeAnchors

# Spara som en annan fil så att vi inte råkar spara över orginalet
@pre.SaveAs "C:/programmering/workspace/groovyseminarie/dokument/generated/generated.pptx"
#  Run
@pre.SlideShowSettings.Run

# close powerpoint, will close all the currently open files
#ppt.Quit()