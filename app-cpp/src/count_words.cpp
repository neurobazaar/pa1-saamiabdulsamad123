#include <iostream>
#include <string>

void count_words(std::string input_dir, std::string output_dir, std::size_t& dataset_size, double& execution_time)
{
    // TO-DO implement count words logic
}

int main(int argc, char** argv)
{
    std::size_t dataset_size = 0;
    double execution_time = 0.0;

    if (argc != 3) {
        std::cerr << "improper number of arguments" << std::endl;
        return 1;
    }

    count_words(std::string(argv[1]), std::string(argv[2]), dataset_size, execution_time);

    std::cout << "Finished counting " << dataset_size << " MiB of words";
    std::cout << " in " << execution_time << " miliseconds" << std::endl;

    return 0;
}
