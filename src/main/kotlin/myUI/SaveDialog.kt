import org.apache.commons.io.FilenameUtils
import java.io.File
import javax.swing.JFileChooser
import javax.swing.filechooser.FileNameExtensionFilter
import javax.swing.filechooser.FileSystemView


fun saveFileChooserDialog(
    title: String,
    fileName: String,
): File? {
    val fileChooser = JFileChooser(FileSystemView.getFileSystemView())
    fileChooser.dialogType = JFileChooser.SAVE_DIALOG
    fileChooser.currentDirectory = File(System.getProperty("user.dir"))
    fileChooser.dialogTitle = title
    fileChooser.fileSelectionMode = JFileChooser.FILES_ONLY
    fileChooser.name = fileName
    fileChooser.isAcceptAllFileFilterUsed = true
    fileChooser.selectedFile = File(fileName)
    fileChooser.currentDirectory = null
    fileChooser.fileFilter = FileNameExtensionFilter("Word", "doc", "docx")
    val file = if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
        if (FilenameUtils.getExtension(fileChooser.selectedFile!!.name).equals("docx", ignoreCase = true)) {
            fileChooser.selectedFile
        } else {
            File(
                fileChooser.selectedFile.parentFile,
                FilenameUtils.getBaseName(fileChooser.selectedFile.name) + ".docx"
            )
        }

    } else {
        null
    }


    println(file)
    return file

}