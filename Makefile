# Makefile
run-dist:
	./build/install/app/bin/app

build:
	make -C app build

run:
	.make -C app run

report:
	make -C app report

