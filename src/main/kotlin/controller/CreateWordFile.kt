package controller

import model.patient.Patient
import org.apache.poi.xwpf.usermodel.XWPFDocument
import screens.log
import java.io.File
import java.io.FileOutputStream
import java.math.BigInteger

fun createDoc(dir: File, pat: Patient) {
    try {
        val fileOutputStream = FileOutputStream(dir)
        val document = XWPFDocument()

        document.createParagraph().createRun().setText("���: ${pat.patientName.value.text}")
        document.createParagraph().createRun().setText("�������: ${pat.patientAge.value.text}")
        document.createParagraph().createRun().setText("���: ${if (pat.patientSex.value) "�������" else "�������"}")
        document.createParagraph().createRun().setText("���� ���������: ${pat.dateOfWork.value.text}")
        document.createParagraph().createRun().setText("����� ���������: ${pat.numberOfCorrection.value.text}")
        document.createParagraph().createRun().setText("")
        document.createParagraph().createRun().setText("��������: ${pat.patientProblem.value.text}")
        document.createParagraph().createRun().setText("""��������: ${pat.barometerLevel.value} - ${pat.barometerPart.value} - ${pat.barometerItem.value}""")
        document.createParagraph().createRun().setText("������� ���������: ${pat.painNumber.value}")
        document.createParagraph().createRun().setText("���������: ${pat.structureLevel.value} � ${pat.structurePart.value} � ${pat.structureType.value}")
        document.createParagraph().createRun().setText("���: ${pat.baxNumber.value}")
        document.createParagraph().createRun().setText("���������: ${pat.baxNumber.value}")

        document.write(fileOutputStream)
        fileOutputStream.close()
        log("���� ������� ������� �� ���� $dir")
//        val wordPackage = WordprocessingMLPackage.createPackage()
//        val mainDocumentPart = wordPackage.mainDocumentPart
//
//        mainDocumentPart.content.add(paragraphCreate("���: ${pat.patientName.value.text}"))
//        mainDocumentPart.content.add(paragraphCreate("�������: ${pat.patientAge.value.text}"))
//        mainDocumentPart.content.add(paragraphCreate("���: ${if (pat.patientSex.value) "�������" else "�������"}"))
//        mainDocumentPart.content.add(paragraphCreate("���� ���������: ${pat.dateOfWork.value.text}"))
//        mainDocumentPart.content.add(paragraphCreate("����� ���������: ${pat.numberOfCorrection.value.text}"))
//        mainDocumentPart.content.add(paragraphCreate(""))
//        mainDocumentPart.content.add(paragraphCreate("��������: ${pat.patientProblem.value.text}"))
//        mainDocumentPart.content.add(
//            paragraphCreate(
//                """��������: ${pat.barometerLevel.value} - ${pat.barometerPart.value} - ${pat.barometerItem.value}""".trimMargin()
//            )
//        )
//        mainDocumentPart.content.add(paragraphCreate("������� ���������: ${pat.painNumber.value}"))
//        mainDocumentPart.content.add(paragraphCreate("���������: ${pat.structureLevel.value} � ${pat.structurePart.value} � ${pat.structureType.value}"))
//        mainDocumentPart.content.add(paragraphCreate("���: ${pat.baxNumber.value}"))
//        mainDocumentPart.content.add(paragraphCreate("���������: ${pat.baxNumber.value}"))
//
//        wordPackage.save(dir)
    } catch (e: Exception) {
        log("����� ${e.stackTraceToString()}")
    }

}

fun createHomework(dir: File, map: MutableMap<String, String>) {
    val fileOutputStream = FileOutputStream(dir)
    val document = XWPFDocument()

    for (i in map.values) {
        document.createParagraph().createRun().setText(i)
        document.createParagraph().createRun().setText("")
    }
    document.write(fileOutputStream)
    fileOutputStream.close()

//    val wordPackage = WordprocessingMLPackage.createPackage()
//    val mainDocumentPart = wordPackage.mainDocumentPart
//
//    for (i in map.values) {
//        mainDocumentPart.content.add(paragraphCreate(i))
//        mainDocumentPart.content.add(paragraphCreate(""))
//    }
//
//    wordPackage.save(dir)
}

//private fun paragraphCreate(text: String): P {
//    val factory = Context.getWmlObjectFactory()
//    val p = factory.createP()
//    val r = factory.createR()
//    val t = factory.createText()
//    val pPr = factory.createPPr()
//
//    //������������� � ������������� ���������
//    val spacing = PPrBase.Spacing()
//    spacing.before = BigInteger.valueOf(0)
//    spacing.after = BigInteger.valueOf(0)
//    spacing.line = BigInteger.valueOf(240)
//    pPr.spacing = spacing
//    p.pPr = pPr
//
//    val rpr = factory.createRPr()
//    //������ ������
//    val measure = factory.createHpsMeasure()
//    measure.`val` = BigInteger.valueOf(28)
//    rpr.sz = measure
//    //����
//    val language = factory.createCTLanguage()
//    language.`val` = "ru-RU"
//    rpr.lang = language
//    r.rPr = rpr
//
//    t.value = text
//    r.content.add(t)
//    p.content.add(r)
//
//    return p
//}
