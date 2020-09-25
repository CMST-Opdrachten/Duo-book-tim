package com.example.demo.controllers;

import com.example.demo.objects.Book;
import com.example.demo.exceptions.BookNotFoundException;
import com.example.demo.repositorys.BookRepo;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

@RestController
public class BookController {

    private final BookRepo repository;
    public Book emptyBook;
    Random rand = new Random();

    private static Logger log = LoggerFactory.getLogger(BookController.class);

    BookController(BookRepo repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Book> getAllBooks() {
        log.info("Get all starting");
        return repository.findAll();
    }

    @RequestMapping(value = "/books/pdf", method = RequestMethod.GET)
    String pdf() throws FileNotFoundException, DocumentException {
        String filename = "pdftests/booklist" + rand.nextInt() + ".pdf";
        Document doc = createPdf(filename);
        doc.open();
        doc.add(addPdfList(repository.findAll(), doc));
        doc.close();
        return "PDF created at " + System.getProperty("user.dir") + "/" + filename;
    }

    @PostMapping("/books")
    Book newBook(@RequestBody Book newBook) {
        return repository.save(newBook);
    }

    @GetMapping("/books/{id}")
    Book one(@PathVariable String id) {
        if (convertToBigInt(id) != null) {
            return repository.findById(convertToBigInt(id)).orElseThrow(() -> new BookNotFoundException(convertToBigInt(id)));
        } else {
            return this.emptyBook;
        }
    }

    @PutMapping("/books/{id}")
    Book replaceBook(@RequestBody Book newBook, @PathVariable String id){
        if (convertToBigInt(id) != null) {
            BigInteger convertedId = convertToBigInt(id);
            return repository.findById(convertedId)
                    .map(book -> {
                        book.setTitle(newBook.getTitle());
                        book.setPublisher(newBook.getTitle());
                        return repository.save(book);
                    })
                    .orElseGet(() -> {
                        newBook.setId(convertedId);
                        return repository.save(newBook);
                    });
        } else {
            return this.emptyBook;
        }
    }

    @DeleteMapping("/book/{id}")
    void deleteBook(@PathVariable String id) {
        if (convertToBigInt(id) != null) {
            repository.deleteById(convertToBigInt(id));
        }
    }

    public BigInteger convertToBigInt(String id) {
        try {
            return new BigInteger(id);
        } catch (Exception e) {
            return null;
        }
    }

    public Document createPdf(String filename) throws FileNotFoundException, DocumentException {
        Document doc = new Document();
        PdfWriter.getInstance(doc, new FileOutputStream(filename));
        return doc;
    }

    public PdfPTable addPdfList(List<Book> data, Document doc) throws DocumentException {
        doc.add(new Paragraph("Table of books:"));
        doc.add(new Paragraph("\n"));
        PdfPTable table = new PdfPTable(3);
        PdfPCell cell = new PdfPCell();
        PdfPCell cell1 = new PdfPCell(new Phrase("Id"));
        PdfPCell cell2 = new PdfPCell(new Phrase("Title"));
        PdfPCell cell3 = new PdfPCell(new Phrase("Publisher"));



        Iterator<Book> bookIterator = data.iterator();

        while (bookIterator.hasNext()) {
            cell1.setPhrase(new Phrase(bookIterator.next().getId().toString()));
            cell2.setPhrase(new Phrase(bookIterator.next().getTitle()));
            cell2.setPhrase(new Phrase(bookIterator.next().getPublisher()));
            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
        }

        return table;
    }
}
