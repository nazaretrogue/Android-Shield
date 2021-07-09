DOC := proyecto.tex

all: mr-proper doc

doc: $(DOC:.tex=.pdf)

all: proyecto.pdf

%.pdf: %.tex
	pdflatex $< && bibtex $* && pdflatex $< && pdflatex $<

mr-proper:
	rm -f *.aux *.bbl *.blg *.lof *.log *.out *.toc *.pdf *.gz



