package pl.edu.agh.idziak;

import pl.edu.agh.idziak.asw.impl.AlgorithmType;
import pl.edu.agh.idziak.asw.utils.test.TestExecutor;
import pl.edu.agh.idziak.asw.utils.test.TestLoader;
import pl.edu.agh.idziak.asw.utils.test.grid2d.io.DTOMapper;
import pl.edu.agh.idziak.asw.utils.test.grid2d.io.TestCaseDTO;
import pl.edu.agh.idziak.asw.utils.test.grid2d.model.TestCase;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Tomasz on 15.03.2017.
 */
public class ASWTestSuite {

    private final List<TestCase> testCases;
    private TestLoader loader = new TestLoader();
    private TestExecutor testExecutor = new TestExecutor();

    public ASWTestSuite(Path path) throws IOException {
        List<TestCaseDTO> testCaseDTOS = loader.openTestsFile(path.toFile());
        testCases = testCaseDTOS.stream().map(DTOMapper::dtoToInternal).collect(Collectors.toList());
    }

    public void executeTest(String id, AlgorithmType algorithmType) {
        checkNotNull(algorithmType);
        Optional<TestCase> foundCase = testCases.stream().filter(testCase -> testCase.getName().equals(id)).findFirst();
        if (!foundCase.isPresent()) {
            throw new RuntimeException("No such test case: " + id);
        }
        testExecutor.run(foundCase.get(), algorithmType);
    }
}
