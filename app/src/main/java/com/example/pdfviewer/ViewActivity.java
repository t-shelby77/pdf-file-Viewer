package com.example.pdfviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnDrawListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener;
import com.github.barteksc.pdfviewer.listener.OnRenderListener;
import com.github.barteksc.pdfviewer.listener.OnTapListener;
import com.krishna.fileloader.FileLoader;
import com.krishna.fileloader.listener.FileRequestListener;
import com.krishna.fileloader.pojo.FileResponse;
import com.krishna.fileloader.request.FileLoadRequest;

import java.io.File;

public class ViewActivity extends AppCompatActivity {

    PDFView pdfView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);


        pdfView = (PDFView)findViewById(R.id.pdf_viewer);
        progressBar = (ProgressBar)findViewById(R.id.progress_bar);

        if (getIntent() != null){
            String viewtype = getIntent().getStringExtra("ViewType");

            if (viewtype != null || !TextUtils.isEmpty(viewtype)) {
                /*
                if (viewtype.equals("assests"))
                {
                //FILE TA REMOVE KORESI FROM ASSET. SO NEXT TIME ADD A FILE NAMED Clss 5.pdf in the ASSET
                    pdfView.fromAsset("Class 5.pdf")
                            .password(null)
                    .defaultPage(0)
                            .enableSwipe(true)
                            .enableDoubletap(true)
                            .swipeHorizontal(true)
                    .onDraw(new OnDrawListener() {
                        @Override
                        public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {
                            //code dibo
                        }
                    })
                            .onDrawAll(new OnDrawListener() {
                                @Override
                                public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {
                                    //code
                                }
                            })
                            .onPageError(new OnPageErrorListener() {
                                @Override
                                public void onPageError(int page, Throwable t) {
                                    Toast.makeText(ViewActivity.this, "ERROR OCCURED WHILE OPENING PAGE", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .onPageChange(new OnPageChangeListener() {
                                @Override
                                public void onPageChanged(int page, int pageCount) {
                                    //code
                                }
                            })
                            .onTap(new OnTapListener() {
                                @Override
                                public boolean onTap(MotionEvent e) {
                                    return true;
                                }
                            }).onRender(new OnRenderListener() {
                        @Override
                        public void onInitiallyRendered(int nbPages, float pageWidth, float pageHeight) {
                            pdfView.fitToWidth();//fixes the screen size
                        }
                    })
                            .enableAnnotationRendering(true)
                            .invalidPageColor(Color.WHITE)
                            .load();


                } */
                if (viewtype.equals("Storage"))
                {
                    Uri pdfFile = Uri.parse(getIntent().getStringExtra("FileUri"));


                    pdfView.fromUri(pdfFile)
                            .password(null)
                            .defaultPage(0)
                            .enableSwipe(true)
                            .enableDoubletap(true)
                            .swipeHorizontal(true)
                            .onDraw(new OnDrawListener() {
                                @Override
                                public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {
                                    //code dibo
                                }
                            })
                            .onDrawAll(new OnDrawListener() {
                                @Override
                                public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {
                                    //code
                                }
                            })
                            .onPageError(new OnPageErrorListener() {
                                @Override
                                public void onPageError(int page, Throwable t) {
                                    Toast.makeText(ViewActivity.this, "ERROR OCCURED WHILE OPENING PAGE", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .onPageChange(new OnPageChangeListener() {
                                @Override
                                public void onPageChanged(int page, int pageCount) {
                                    //code
                                }
                            })
                            .onTap(new OnTapListener() {
                                @Override
                                public boolean onTap(MotionEvent e) {
                                    return true;
                                }
                            }).onRender(new OnRenderListener() {
                        @Override
                        public void onInitiallyRendered(int nbPages, float pageWidth, float pageHeight) {
                            pdfView.fitToWidth();//fixes the screen size
                        }
                    })
                            .enableAnnotationRendering(true)
                            .invalidPageColor(Color.WHITE)
                            .load();

                }
                /*
                else if (viewtype.equals("internet")) {

                    progressBar.setVisibility(View.VISIBLE);//shows progress bar

                    FileLoader.with(this)
                            //TAKE INPUT HERE
                            .load("http://www.africau.edu/images/default/sample.pdf")
                            .fromDirectory("PDFfile",FileLoader.DIR_EXTERNAL_PUBLIC)
                            .asFile(new FileRequestListener<File>() {
                                @Override
                                public void onLoad(FileLoadRequest request, FileResponse<File> response) {
                                    progressBar.setVisibility(View.GONE);

                                    File pdfFile = response.getBody();
                                    pdfView.fromFile(pdfFile)
                                            .password(null)
                                            .defaultPage(0)
                                            .enableSwipe(true)
                                            .enableDoubletap(true)
                                            .swipeHorizontal(true)
                                            .onDraw(new OnDrawListener() {
                                                @Override
                                                public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {
                                                    //code dibo
                                                }
                                            })
                                            .onDrawAll(new OnDrawListener() {
                                                @Override
                                                public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {
                                                    //code
                                                }
                                            })
                                            .onPageError(new OnPageErrorListener() {
                                                @Override
                                                public void onPageError(int page, Throwable t) {
                                                    Toast.makeText(ViewActivity.this, "ERROR OCCURED WHILE OPENING PAGE", Toast.LENGTH_SHORT).show();
                                                }
                                            })
                                            .onPageChange(new OnPageChangeListener() {
                                                @Override
                                                public void onPageChanged(int page, int pageCount) {
                                                    //code
                                                }
                                            })
                                            .onTap(new OnTapListener() {
                                                @Override
                                                public boolean onTap(MotionEvent e) {
                                                    return true;
                                                }
                                            }).onRender(new OnRenderListener() {
                                        @Override
                                        public void onInitiallyRendered(int nbPages, float pageWidth, float pageHeight) {
                                            pdfView.fitToWidth();//fixes the screen size
                                        }
                                    })
                                            .enableAnnotationRendering(true)
                                            .invalidPageColor(Color.WHITE)
                                            .load();

                                }

                                @Override
                                public void onError(FileLoadRequest request, Throwable t) {

                                    Toast.makeText(ViewActivity.this, ""+ t.getMessage() , Toast.LENGTH_SHORT).show();
                                    progressBar.setVisibility(View.GONE);
                                }
                            });

                } */
            }
        }

    }
}
