all: diff

diff: files
	-diff out1.txt out2.txt
	-diff out2.txt out_naive.txt

files: out1.txt out2.txt out_naive.txt

out1.txt: speech.txt speech1.sed
	./speech1.sed speech.txt > out1.txt
out2.txt: speech.txt speech2.sed
	./speech2.sed speech.txt > out2.txt
out_naive.txt: speech.txt speech_naive.sed
	./speech_naive.sed speech.txt > out_naive.txt

clean:
	-rm -rf out1.txt out2.txt out_naive.txt
