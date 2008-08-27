require 'win32ole'

ppt = WIN32OLE.new('Powerpoint.Application')
ppt.Visible = true
@pre = ppt.presentations.Open("C:/programmering/workspace/Groovyseminarie/dokument/groovyseminarium_source.pptx")
@regexp = /\[\w+\.\w+\]/

def replaceCodeAnchors
  @pre.Slides.each{|slide|
    slide.Shapes.each{|shape|
      replace( shape, slide ) if @regexp =~ shape.TextFrame.TextRange.Text
    }
  }
end

def replace(ole_shape, ole_slide)
  begin
    fileName = ole_shape.TextFrame.TextRange.Text
    fileName = fileName.scan(@regexp)[0]
    fileName["["]=""
    fileName["]"]=""
    #  fileName += ".groovy"
    fileUrl = ""
    if fileName =~ /\.groovy/
      fileUrl = "C:/programmering/workspace/groovyseminarie/src/se/britech/groovySeminarie/groovy/#{fileName}"
    else
      fileUrl = "C:/programmering/workspace/groovyseminarie/src/se/britech/groovySeminarie/java/#{fileName}"
    end
    
    text = ""
    File.open(fileUrl, "r").each{|l| text += l unless l =~ /^package / or l =~ /^import / }
    
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
    
    generate_ruby_script fileName, adress
  rescue StandardError => e
    p "Ett fel inträffade #{e}"
  end

end

def generate_ruby_script(groovyFile, rubyFile)
  f = File.new(rubyFile, "w+")
  f.puts "require 'C:/programmering/workspace/groovyseminarie/ruby/file_in_ide.rb'
  FileInIde.open \"#{groovyFile}\""
  f.close
end

replaceCodeAnchors

# Spara som en annan fil så att vi inte råkar spara över orginalet
@pre.SaveAs "C:/programmering/workspace/groovyseminarie/dokument/generated/generated.pptx"
#  Run
@pre.SlideShowSettings.Run

# close powerpoint, will close all the currently open files
#ppt.Quit()