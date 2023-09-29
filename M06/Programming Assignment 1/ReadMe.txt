Dependencies:
- JavaFX (All Libraries)
- mysql-connector-j-8.1.0

How To Run:
- This program is run with the View_Insert_Update_DB.jar file
    - The command to then test that jar file is (Note: substitute 'View_Insert_Update_DB' for your jar file name and the paths to javafx/lib and mysql-connector-j-8.1.0 with your own paths to these libraries)
        (Also remember to run this command from the folder containing your jar file (default dist) )
        java --module-path "c:\\Users\\field\\Documents\\JavaFX\\openjfx-17.0.8_windows-x64_bin-sdk\\javafx-sdk-17.0.8\\lib/" --add-modules javafx.controls,javafx.base,javafx.fxml,javafx.graphics,javafx.media,javafx.web -classpath c:\\Users\\field\\Documents\\mysql-connector-j-8.1.0\\mysql-connector-j-8.1.0 -jar --enable-preview View_Insert_Update_DB.jar

What This Is:
- This program is a database editor that allows the user to View, Insert and Update the rows in the database