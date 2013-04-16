package org.unix4j.unix;

import org.junit.Ignore;
import org.junit.Test;
import org.unix4j.Unix4j;
import org.unix4j.context.DefaultExecutionContext;
import org.unix4j.context.ExecutionContext;
import org.unix4j.context.ExecutionContextFactory;

import java.io.File;

public class FindFileTest {

	private static final class Config implements ExecutionContextFactory {
		private final CommandFileTest tester;
        private final File currentDirectory;

        public Config(final CommandFileTest tester, final File currentDirectory) {
            this.tester = tester;
            this.currentDirectory = currentDirectory;
        }
		@Override
		public ExecutionContext createExecutionContext() {
			final DefaultExecutionContext context = new DefaultExecutionContext();
            if(currentDirectory != null){
                context.setCurrentDirectory(currentDirectory);
            } else {
                context.setCurrentDirectory(tester.getInputFile());
            }
			return context;
		}
	};


    //(cd default.input && find .)
    @Test
    @Ignore
    public void find_currentDir(){
        final CommandFileTest tester = new CommandFileTest(this.getClass());
        final File currentDirectory = new File(tester.getInputFile().getParentFile().getPath() + "/default.input");
        final Config config = new Config(tester, currentDirectory);
        tester.run(Unix4j.use(config).find("."));
    }

    //(cd default.input && find ./)
    @Test
    @Ignore
    public void find_currentDirWithDotSlash(){
        final CommandFileTest tester = new CommandFileTest(this.getClass());
        final File currentDirectory = new File(tester.getInputFile().getParentFile().getPath() + "/default.input");
        final Config config = new Config(tester, currentDirectory);
        tester.run(Unix4j.use(config).find("./"));
    }

    //(cd default.input/folder && find ../)
    @Test
    @Ignore
    public void find_parentPath(){
        final CommandFileTest tester = new CommandFileTest(this.getClass());
        final File currentDirectory = new File(tester.getInputFile().getParentFile().getPath() + "/default.input/folder");
        final Config config = new Config(tester, currentDirectory);
        tester.run(Unix4j.use(config).find("../"));
    }

    //(cd default.input/mydir/SUBDIR && find ../../)
    @Test
    @Ignore
    public void find_parentOfParentPath(){
        final CommandFileTest tester = new CommandFileTest(this.getClass());
        final File currentDirectory = new File(tester.getInputFile().getParentFile().getPath() + "/default.input/mydir/SUBDIR");
        final Config config = new Config(tester, currentDirectory);
        tester.run(Unix4j.use(config).find("../../"));
    }

    //(cd default.input/mydir && find ../mydir)
    @Test
    @Ignore
    public void find_parentBackToChild(){
        final CommandFileTest tester = new CommandFileTest(this.getClass());
        final File currentDirectory = new File(tester.getInputFile().getParentFile().getPath() + "/default.input/mydir");
        final Config config = new Config(tester, currentDirectory);
        tester.run(Unix4j.use(config).find("../mydir"));
    }

    //(cd default.input && find mydir)
    @Test
    @Ignore
    public void find_specifiedDir(){
        final CommandFileTest tester = new CommandFileTest(this.getClass());
        final File currentDirectory = new File(tester.getInputFile().getParentFile().getPath() + "/default.input");
        final Config config = new Config(tester, currentDirectory);
        tester.run(Unix4j.use(config).find("mydir"));
    }

    //(cd default.input && find mydir/SUBDIR)
    @Test
    @Ignore
    public void find_specifiedSubDir(){
        final CommandFileTest tester = new CommandFileTest(this.getClass());
        final File currentDirectory = new File(tester.getInputFile().getParentFile().getPath() + "/default.input");
        final Config config = new Config(tester, currentDirectory);
        tester.run(Unix4j.use(config).find("mydir/SUBDIR"));
    }

    //(cd default.input && find ./mydir)
    @Test
    @Ignore
    public void find_specifiedDirWithDotSlash(){
        final CommandFileTest tester = new CommandFileTest(this.getClass());
        final File currentDirectory = new File(tester.getInputFile().getParentFile().getPath() + "/default.input");
        final Config config = new Config(tester, currentDirectory);
        tester.run(Unix4j.use(config).find("./mydir"));
    }

    //(cd default.input && find ./mydir/SUBDIR)
    @Test
    @Ignore
    public void find_specifiedSubDirWithDotSlash(){
        final CommandFileTest tester = new CommandFileTest(this.getClass());
        final File currentDirectory = new File(tester.getInputFile().getParentFile().getPath() + "/default.input");
        final Config config = new Config(tester, currentDirectory);
        tester.run(Unix4j.use(config).find("./mydir/SUBDIR"));
    }

    //(cd default.input && find . -type f)
    @Test
    @Ignore
    public void find_typeFile(){
        final CommandFileTest tester = new CommandFileTest(this.getClass());
        final File currentDirectory = new File(tester.getInputFile().getParentFile().getPath() + "/default.input");
        final Config config = new Config(tester, currentDirectory);
        tester.run(Unix4j.use(config).find(Find.Options.typeFile, currentDirectory.getPath()));
    }

    //(cd default.input && find . -type d)
    @Test
    @Ignore
    public void find_typeDir(){
        final CommandFileTest tester = new CommandFileTest(this.getClass());
        final File currentDirectory = new File(tester.getInputFile().getParentFile().getPath() + "/default.input");
        final Config config = new Config(tester, currentDirectory);
        tester.run(Unix4j.use(config).find(Find.Options.typeDirectory, currentDirectory.getPath()));
    }

    //(cd default.input && find . -type f -name a.txt)
    @Test
    @Ignore
    public void find_typeFileWithName(){
        final CommandFileTest tester = new CommandFileTest(this.getClass());
        final File currentDirectory = new File(tester.getInputFile().getParentFile().getPath() + "/default.input");
        final Config config = new Config(tester, currentDirectory);
        tester.run(Unix4j.use(config).find(Find.Options.typeFile, currentDirectory.getPath(), "a.txt"));
        tester.run(Unix4j.use(config).find(Find.Options.f, currentDirectory.getPath(), "a.txt"));
    }

    //(cd default.input && find . -name bb.*)
    @Test
    @Ignore
    public void find_withNameWildcard(){
        final CommandFileTest tester = new CommandFileTest(this.getClass());
        final File currentDirectory = new File(tester.getInputFile().getParentFile().getPath() + "/default.input");
        final Config config = new Config(tester, currentDirectory);
        tester.run(Unix4j.use(config).find(currentDirectory.getPath(), "bb.*"));
    }

    //(cd default.input && find . -name bb.t*)
    @Test
    @Ignore
    public void find_withNameWildcard2(){
        final CommandFileTest tester = new CommandFileTest(this.getClass());
        final File currentDirectory = new File(tester.getInputFile().getParentFile().getPath() + "/default.input");
        final Config config = new Config(tester, currentDirectory);
        tester.run(Unix4j.use(config).find(currentDirectory.getPath(), "bb.t*"));
    }

    //(cd default.input && find . -type f -iname bb.txt)
    @Test
    @Ignore
    public void find_typeFileWithNameIgnoreCase(){
        final CommandFileTest tester = new CommandFileTest(this.getClass());
        final File currentDirectory = new File(tester.getInputFile().getParentFile().getPath() + "/default.input");
        final Config config = new Config(tester, currentDirectory);
        tester.run(Unix4j.use(config).find(Find.Options.ignoreCase, currentDirectory.getPath(), "bb.txt"));
        tester.run(Unix4j.use(config).find(Find.Options.i, currentDirectory.getPath(), "bb.txt"));
    }

    //(cd default.input && find . -iname bb.t*)
    @Test
    @Ignore
    public void find_withNameWildcardIgnoreCae(){
        final CommandFileTest tester = new CommandFileTest(this.getClass());
        final File currentDirectory = new File(tester.getInputFile().getParentFile().getPath() + "/default.input");
        final Config config = new Config(tester, currentDirectory);
        tester.run(Unix4j.use(config).find(Find.Options.ignoreCase, currentDirectory.getPath(), "bb.t*"));
        tester.run(Unix4j.use(config).find(Find.Options.i, currentDirectory.getPath(), "bb.t*"));
    }

    //(cd default.input && find -E . -regex "a.*\.txt")
    @Test
    @Ignore
    public void find_regexWithNoMatchesAsIsMatchingWholePath(){
        final CommandFileTest tester = new CommandFileTest(this.getClass());
        final File currentDirectory = new File(tester.getInputFile().getParentFile().getPath() + "/default.input");
        final Config config = new Config(tester, currentDirectory);
        tester.run(Unix4j.use(config).find(Find.Options.regex, currentDirectory.getPath(), "a.*\\.txt"));
        tester.run(Unix4j.use(config).find(Find.Options.r, currentDirectory.getPath(), "a.*\\.txt"));
    }

    //(cd default.input && find -E . -regex ".*a\.txt")
    @Test
    @Ignore
    public void find_regexSimple(){
        final CommandFileTest tester = new CommandFileTest(this.getClass());
        final File currentDirectory = new File(tester.getInputFile().getParentFile().getPath() + "/default.input");
        final Config config = new Config(tester, currentDirectory);
        tester.run(Unix4j.use(config).find(Find.Options.regex, currentDirectory.getPath(), ".*a\\.txt"));
        tester.run(Unix4j.use(config).find(Find.Options.r, currentDirectory.getPath(), ".*a\\.txt"));
    }

    //(cd default.input && find . -type f -size +1k)
    @Test
    @Ignore
    public void find_sizesLargerThanOneK(){
        final CommandFileTest tester = new CommandFileTest(this.getClass());
        final File currentDirectory = new File(tester.getInputFile().getParentFile().getPath() + "/default.input");
        final Config config = new Config(tester, currentDirectory);
        tester.run(Unix4j.use(config).find(currentDirectory.getPath(), 1024));
    }

    //(cd default.input && find . -type f -size -1k)
    @Test
    @Ignore
    public void find_sizesSmallerThanOneK(){
        final CommandFileTest tester = new CommandFileTest(this.getClass());
        final File currentDirectory = new File(tester.getInputFile().getParentFile().getPath() + "/default.input");
        final Config config = new Config(tester, currentDirectory);
        tester.run(Unix4j.use(config).find(currentDirectory.getPath(), -1024));
        tester.run(Unix4j.use(config).find(currentDirectory.getPath(), -1024));
    }

    //(cd default.input && find . -type f -size -1k -name aa.*)
    @Test
    @Ignore
    public void find_sizesSmallerThanOneKWithGivenName(){
        final CommandFileTest tester = new CommandFileTest(this.getClass());
        final File currentDirectory = new File(tester.getInputFile().getParentFile().getPath() + "/default.input");
        final Config config = new Config(tester, currentDirectory);
        tester.run(Unix4j.use(config).find(currentDirectory.getPath(), -1024, "aa.*"));
        tester.run(Unix4j.use(config).find(currentDirectory.getPath(), -1024, "aa.*"));
    }
}