"""
tools.grammar_gen.py

Generate the associated file of the '.g' file with ANTLR
"""

from pathlib2 import Path
from subprocess import call


"""ANTLR jar file"""
ANTLR_PATH = 'libs/antlr/antlr-3.5.2-complete.jar'

"""ANTLR output folder"""
ANTLR_OUT_PATH = 'src/antlr/'

"""Grammar source folder"""
SOURCE_PATH = 'assets/'


def fetch_grammar_file() -> Path:
    """Fetch and test the source file

    :return: return its path as a Path object
    """

    # locate source folder
    source_folder = Path(__file__)\
        .parent\
        .parent\
        .joinpath(SOURCE_PATH)

    if not source_folder.exists():
        exit(f'Missing source folder at {source_folder}')

    # extract grammar file
    sources = list(source_folder.glob('**/*.g'))

    if len(sources) != 1:
        formatted_files = \
            '\n\t- ' + '\n\t- '.join([str(f) for f in sources]) \
            if sources \
            else ''

        exit(
            f'Less or more than one grammar file was found.'
            f'{formatted_files}')

    return sources[0]


def exec_antlr(grammar_file: Path) -> None:
    """Execute ANTLR .jar for the given grammar
    """

    # locate the .jar file
    jar_path = Path(__file__)\
        .parent\
        .parent\
        .joinpath(ANTLR_PATH)

    if not jar_path.exists():
        exit(f'Missing .jar `{jar_path}`')

    # generate output folder
    out_path = Path(__file__) \
        .parent \
        .parent \
        .joinpath(ANTLR_OUT_PATH)

    # execute ANTLR on grammar file
    call(['java', '-jar', f"{str(jar_path)}", str(grammar_file), '-o', str(out_path)])


if __name__ == "__main__":
    source = fetch_grammar_file()
    exec_antlr(source)
