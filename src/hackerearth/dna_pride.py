class Solution:
    def input(self):
        inputs = []

        t = int(raw_input())

        for i in range(t):
            raw_input()
            inputs.append(raw_input())

        return inputs

    def print_output(self, outputs):
        for output in outputs:
            print(output)

    def get_base_seq(self, dna_seqs):
        seq_map = {'A': 'T', 'T': 'A', 'G': 'C', 'C': 'G'}
        outputs = []

        for dna_seq in dna_seqs:
            output = ""

            for ch in dna_seq:
                if ch in seq_map:
                    output += seq_map[ch]
                else:
                    output = "Error RNA nucleobases found!"
                    break

            outputs.append(output)

        return outputs


solution = Solution()
input_sequence = solution.input()
outputs = solution.get_base_seq(input_sequence)
solution.print_output(outputs)
