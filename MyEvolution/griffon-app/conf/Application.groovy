application {
    title = 'MyEvolution'
    startupGroups = ['MyEvolution']

    // Should Griffon exit when no Griffon created frames are showing?
    autoShutdown = true

    // If you want some non-standard application class, apply it here
    //frameClass = 'javax.swing.JFrame'
}
mvcGroups {
    // MVC Group for "MyEvolution"
    'MyEvolution' {
        model = 'myevolution.MyEvolutionModel'
        controller = 'myevolution.MyEvolutionController'
        view = 'myevolution.MyEvolutionView'
    }

}
