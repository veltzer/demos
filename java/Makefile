##############
# parameters #
##############

# do you want dependency on the makefile itself ?!?
DO_ALL_DEPS:=1

# dependency on the makefile itself
ifeq ($(DO_ALL_DEPS),1)
ALL_DEPS:=Makefile
else
ALL_DEPS:=
endif

# silent stuff
ifeq ($(DO_MKDBG),1)
Q:=
# we are not silent in this branch
else # DO_MKDBG
Q:=@
#.SILENT:
endif # DO_MKDBG

# sources from the git perspective
GIT_SOURCES:=$(shell ../scripts/git_wrapper.sh ls-files)
ALL:=
CLEAN:=
CLEAN_DIRS:=

# java section
JAVA_SOURCE_DIR:=src
JAVA_BIN:=bin
JAVA_SOURCES:=$(shell ../scripts/find_wrapper.sh $(JAVA_SOURCE_DIR) -name "*.java")
JAVA_COMPILE_STAMP:=java_compile.stamp
JAVA_CLASSPATH:=$(shell scripts/get_java_jars.pl classpath)
ifneq ($(JAVA_SOURCES),)
ALL:=$(ALL) $(JAVA_COMPILE_STAMP)
CLEAN_DIRS:=$(CLEAN_DIRS) $(JAVA_BIN)
CLEAN:=$(CLEAN) $(JAVA_COMPILE_STAMP) java.hprof.txt
endif

# generic section
.PHONY: all
all: $(ALL)

.PHONY: clean_manual
clean_manual: java_clean
	$(info doing [$@])
	$(Q)-rm -f $(CLEAN)
	$(Q)-rm -rf $(CLEAN_DIRS)

# -x: remove everything not known to git (not only ignore rules).
# -d: remove directories also.
# -f: force.
# I used to do:
# @git clean -xdf
# but it is too harsh
GIT_CLEAN_FLAGS=-xdf
#GIT_CLEAN_FLAGS=-fXd
.PHONY: clean
clean:
	$(info doing [$@])
	$(Q)git clean $(GIT_CLEAN_FLAGS) > /dev/null
.PHONY: clean_test
clean_test:
	@git clean $(GIT_CLEAN_FLAGS) --dry-run

# the reason that tar and gzip were selected and not zip for cpp is that the build system
# for the cpp demos requires scripts with permissions and stuff. This may be different
# for other languages where the permission stuff is not that important (e.g. php).
.PHONY: archive_extremejava
archive_extremejava:
	$(info doing [$@])
	$(Q)git archive --format=tar --prefix=java_extreme HEAD java/src/extreme | gzip > /tmp/java_extreme.tar.gz

.PHONY: git_maintain
git_maintain:
	$(info doing [$@])
	$(Q)git gc

.PHONY: debug
debug:
	$(info ALL is $(ALL))
	$(info CLEAN is $(CLEAN))
	$(info CLEAN_DIRS is $(CLEAN_DIRS))
	$(info GIT_SOURCES is $(GIT_SOURCES))
	$(info ALL_DEPS is $(ALL_DEPS))
	$(info JAVA_CLASSPATH is $(JAVA_CLASSPATH))

# various file finds...
PROJECTS_EXPR:=-name ".project" -or -name ".cproject" -or -wholename "./nbproject/*"
SOURCE_EXPR:=-name "*.cc" -or -name "*.hh" -or -name "*.h" -or -name "*.c" -or -name "Makefile" -or -name "*.txt" -or -name "*.sed" -or -name "*.patch" -or -name "*.mk" -or -name "*.cfg" -or -name "*.sh" -or -name "*.cfg" -or -name "*.html" -or -name "*.css" -or -name "*.js" -or -name "*.ajax" -or -name "*.php" -or -name "*.gdb" -or -name ".gitignore" -or -name "*.pl" -or $(PROJECTS_EXPR) -or -name "*.java" -or -name "*.gif" -or -name "*.png" -or -name "*.xml" -or -name "*.sxw" -or -name "*.sxg" -or -wholename "*/.settings/*" -or -name "*.doc" -or -name "*.pdf" -or -name "*.jar" -or -name ".classpath" -or -name "*.sqlite" -or -name "*.py"
TARGET_EXPR:=-name "*.exe" -or -name "*.d" -or -name "*.o" -or -name "*.so" -or -name "*.o.cmd" -or -name "*.ko" -or -name "*.ko.cmd" -or -wholename "*/.tmp_versions/*" -or -name "Module.symvers" -or -name "modules.order" -or -name "*.class" -or -name "*.stamp" -or -name "*.dis"
GIT_SOURCE_EXPR:=-type f $(addprefix -or -path ./,$(GIT_SOURCES))

.PHONY: find_not_source
find_not_source:
	-@find -type f -not -path "./.git/*" -and -not \( $(SOURCE_EXPR) \)
.PHONY: find_not_target
find_not_target:
	-@find -type f -not -path "./.git/*" -and -not \( $(TARGET_EXPR) \)
.PHONY: find_not_source_target
find_not_source_target:
	-@find -type f -not -path "./.git/*" -and -not \( $(SOURCE_EXPR) \) -and -not \( $(TARGET_EXPR) \)
.PHONY: find_not_git_target
find_not_git_target:
	-@find -type f -and -not \( $(GIT_SOURCE_EXPR) \) -and -not \( $(TARGET_EXPR) \)
.PHONY: find_not_git_source
find_not_git_source:
	-@find -type f -and -not \( $(GIT_SOURCE_EXPR) \)
.PHONY: find_exercises
find_exercises:
	-@find -type f -name "*_exercise.txt"

# code beautifucation
.PHONY: do_astyle
do_astyle: $(ALL_DEPS)
	astyle --verbose --suffix=none --formatted --preserve-date --options=scripts/astyle.cfg $(ALL_C) $(ALL_CC) $(ALL_H) $(ALL_HH)
# I do not use uncrustify because it changes code that it already beautified...
.PHONY: do_uncrustify
do_uncrustify:
	uncrustify -c scripts/uncrustify.cfg --replace --no-backup $(ALL_C) $(ALL_CC) $(ALL_H) $(ALL_HH)

# java section

# the || exit 0 is to prevent make from issueing the haunting "error ignored"
# message which I do not want to see...
$(JAVA_COMPILE_STAMP): $(JAVA_SOURCES) $(ALL_DEPS)
	$(info doing [$@])
	$(Q)mkdir $(JAVA_BIN) 2> /dev/null || exit 0
	$(Q)javac -classpath $(JAVA_CLASSPATH) -d $(JAVA_BIN) -Xlint:unchecked $(JAVA_SOURCES)
	$(Q)touch $(JAVA_COMPILE_STAMP)

.PHONY: java_run
java_run: $(JAVA_COMPILE_STAMP) $(ALL_DEPS)
	$(info doing [$@])
	$(Q)java -classpath $(JAVA_CLASSPATH):$(JAVA_BIN) extreme.profile.Main

.PHONY: java_prof
java_prof: $(JAVA_COMPILE_STAMP) $(ALL_DEPS)
	$(info doing [$@])
	$(Q)java -Xrunhprof:cpu=y -classpath $(JAVA_BIN) extreme.profile.Main

.PHONY: java_clean
java_clean: $(ALL_DEPS)
	$(info doing [$@])
	$(Q)-rm -rf $(JAVA_BIN) $(JAVA_COMPILE_STAMP) java.hprof.txt

.PHONY: sloccount
sloccount:
	$(info doing [$@])
	$(Q)sloccount .

.PHONY: check_author
check_author:
	$(info doing [$@])
	$(Q)find . -name "*.java" -exec grep -H @author {} \; | grep -v "Mark Veltzer"
.PHONY: check_ws
check_ws:
	$(info doing [$@])
	$(Q)git grep "  " -- '*.java'
