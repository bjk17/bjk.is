.PHONY : all clearpdf cleancv timestampindex
all : clearpdf cv.pdf cv-full.pdf cleancv timestampindex

clearpdf:
	rm -f cv{,-full}.pdf

cv.pdf: cv.tex
	pdflatex cv.tex
	pdflatex cv.tex

cv-full.pdf: cv.tex
	sed -e 's/^%~ //' cv.tex > cv-full.tex
	pdflatex cv-full.tex
	pdflatex cv-full.tex
	rm -f cv-full.tex

cleancv:
	rm -f cv{,-full}.{aux,log,out}

timestampindex:
	sed -i.bak "s/time datetime.*</time datetime=\"$$(LC_LOCALE=c date +'%Y-%m-%d')\">$$(LC_LOCALE=c date +'%B %d, %Y')</g" index.html
	rm -f index.html.bak
