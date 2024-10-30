import zstandard as zstd
import io

def zst_to_csv_streaming(zst_file, csv_file):
    """Decompress a .zst file to .csv using streaming."""
    with open(zst_file, 'rb') as f_in:
        decompressor = zstd.ZstdDecompressor()

        # Use a stream reader to handle large files
        with decompressor.stream_reader(f_in) as reader:
            with open(csv_file, 'w', encoding='utf-8') as f_out:
                # Read in chunks and write to the output CSV file
                for chunk in io.TextIOWrapper(reader, encoding='utf-8'):
                    f_out.write(chunk)

# Usage Example
zst_to_csv_streaming('mnq_1hr.csv.zst', 'mnq_1hr.csv')
print("Streaming decompression completed successfully!")
