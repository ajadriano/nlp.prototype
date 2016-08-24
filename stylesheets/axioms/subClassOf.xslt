<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text" indent="no"/>
<xsl:strip-space elements="*"/>

<xsl:template match="S[count(S)=0][NP[*[@det and position()=1]][*[@nsubj]] and VP[(VB|VBP)[position()=1 and @cop]][NN|NNS]]">
SubClassOf(
        <xsl:apply-templates select="NP"/>
	<xsl:text> </xsl:text>
	<xsl:call-template name="noun_to_class">
            <xsl:with-param name="noun" select="VP/(NN|NNS)" />
	</xsl:call-template>)
</xsl:template>

<xsl:template match="S[count(S)=0][NP[*[@det and position()=1]][*[@nsubj]] and VP[MD[@aux] and VP[(VB|VBP)[position()=1 and @cop]][NP[count(NP)=0 and count(SBAR)=0]]]]">
SubClassOf(
	<xsl:call-template name="noun_phrase_to_class">
            <xsl:with-param name="NP" select="NP" />
	</xsl:call-template>
	<xsl:text> </xsl:text>
	<xsl:call-template name="noun_phrase_to_class">
            <xsl:with-param name="NP" select="VP/VP/NP" />
	</xsl:call-template>)
</xsl:template>

<xsl:template match="S[count(S)=0][NP[*[@det and position()=1]][*[@nsubj]] and VP[MD[@aux] and VP[(VB|VBP)[position()=1 and @cop]][NP[count(NP)>0 and count(CC)>0]]]]">
SubClassOf(
	<xsl:call-template name="noun_phrase_to_class">
            <xsl:with-param name="NP" select="NP" />
	</xsl:call-template>
	<xsl:text> </xsl:text>
        <xsl:apply-templates select="VP/VP/NP"/>)
</xsl:template>

<xsl:template match="S[count(S)=0][NNS[@nsubj] and VP[VBP[position()=1 and @cop]][NNS]]">
SubClassOf(
	<xsl:call-template name="noun_to_class">
		<xsl:with-param name="noun" select="NNS" />
	</xsl:call-template>
	<xsl:text> </xsl:text>
	<xsl:call-template name="noun_to_class">
		<xsl:with-param name="noun" select="VP/NNS" />
	</xsl:call-template>)
</xsl:template>

<xsl:template match="S[count(S)=0][NP[(NN|NNS)[@nsubj]][count(NP)>0] and VP[VBP[position()=1 and @cop]][NNS]]">
SubClassOf(
	<xsl:apply-templates select="NP"/>
	<xsl:text> </xsl:text>
	<xsl:call-template name="noun_to_class">
		<xsl:with-param name="noun" select="VP/NNS" />
	</xsl:call-template>)
</xsl:template>

<xsl:template match="S[count(S)=0][NNS[@nsubj] and VP[VBP[position()=1 and @cop] and RB[@lemma='either']][NP[count(NNS)>0 and count(CC)>0]]]">
SubClassOf(
	<xsl:call-template name="noun_to_class">
		<xsl:with-param name="noun" select="NNS" />
	</xsl:call-template>
	<xsl:text> </xsl:text>
	<xsl:apply-templates select="VP/NP"/>)
</xsl:template>

<xsl:template match="S[count(S)=0][NP[*[@nsubj]] and VP[VBP[position()=1 and @cop] and RB[@lemma='either']][NP[count(NNS)>0 and count(CC)>0]]]">
SubClassOf(
	<xsl:apply-templates select="NP"/>
	<xsl:text> </xsl:text>
	<xsl:apply-templates select="VP/NP"/>)
</xsl:template>

<xsl:template match="S[count(S)=0][NP[count(NP)=0 and count(CC)>0 and count(NNS)>0] and VP[VBP[position()=1 and @cop]][NNS]]">
<xsl:for-each select="NP/NNS">
SubClassOf(
    <xsl:call-template name="noun_to_class">
            <xsl:with-param name="noun" select="." />
    </xsl:call-template>
    <xsl:text> </xsl:text>
    <xsl:call-template name="noun_to_class">
            <xsl:with-param name="noun" select="../../VP/NNS" />
    </xsl:call-template>)
</xsl:for-each>
</xsl:template>

<xsl:template match="S[count(S)=0][NP[.//(NN|NNS)[@nsubj]] and VP[(VBP|VBZ)[position()=1 and @cop]][NP[count(NP)=0 and count(SBAR)=0 and count(CC)=0]]]">
SubClassOf(
	<xsl:apply-templates select="NP"/>
	<xsl:text> </xsl:text>
	<xsl:call-template name="noun_phrase_to_class">
		<xsl:with-param name="NP" select="VP/NP" />
	</xsl:call-template>)
</xsl:template>

<xsl:template match="S[count(S)=0][NP[(NN|NNS)[@nsubj]] and VP[(VBP|VBZ)[position()=1 and @cop]][NP[count(NP)>0 and count(SBAR)=0 and count(CC)>0]]]">
SubClassOf(
	<xsl:call-template name="noun_phrase_to_class">
		<xsl:with-param name="NP" select="NP" />
	</xsl:call-template>
	<xsl:text> </xsl:text>
	<xsl:apply-templates select="VP/NP"/>)
</xsl:template>

<xsl:template match="S[count(S)=0][NP[name(*[1])='DT' and NNP[@nsubj]] and VP[(VBP|VBZ)[position()=1 and @cop]][NP[count(NP)=0 and count(SBAR)=0]]]">
SubClassOf(
	<xsl:call-template name="noun_phrase_to_class">
		<xsl:with-param name="NP" select="NP" />
	</xsl:call-template>
	<xsl:text> </xsl:text>
	<xsl:call-template name="noun_phrase_to_class">
		<xsl:with-param name="NP" select="VP/NP" />
	</xsl:call-template>)
</xsl:template>

<xsl:template match="S[count(S)=0][VP/VBZ/@id=@root and NP[*[@entity]] and VP[NNS[count(@entity)=0]]]">
SubClassOf(
	<xsl:call-template name="noun_to_class">
		<xsl:with-param name="noun" select="VP/NNS" />
	</xsl:call-template>
	<xsl:text> </xsl:text>
	ObjectHasValue(ObjectInverseOf(
        <xsl:call-template name="verb_to_object_property">
		<xsl:with-param name="verb" select="VP/VBZ" />
	</xsl:call-template>) 
        <xsl:text> </xsl:text>
	<xsl:call-template name="noun_phrase_to_individual">
            <xsl:with-param name="NP" select="NP" />
        </xsl:call-template>))
</xsl:template>

<xsl:template match="S[count(S)=0][VP/VBZ/@id=@root and NN|NNP[@entity] and VP[NNS[count(@entity)=0]]]">
SubClassOf(
	<xsl:call-template name="noun_to_class">
		<xsl:with-param name="noun" select="VP/NNS" />
	</xsl:call-template>
	<xsl:text> </xsl:text>
	ObjectHasValue(ObjectInverseOf(
        <xsl:call-template name="verb_to_object_property">
		<xsl:with-param name="verb" select="VP/VBZ" />
	</xsl:call-template>) 
        <xsl:text> </xsl:text>
	<xsl:call-template name="noun_to_individual">
            <xsl:with-param name="noun" select="NN|NNP" />
        </xsl:call-template>))
</xsl:template>

<xsl:template match="S[count(S)=0][VP/VBZ/@id=@root and NN|NNP[@entity] and VP[NP[count(*[@entity])=0]]]">
SubClassOf(
	<xsl:call-template name="noun_phrase_to_class">
		<xsl:with-param name="NP" select="VP/NP" />
	</xsl:call-template>
	<xsl:text> </xsl:text>
	ObjectHasValue(ObjectInverseOf(
        <xsl:call-template name="verb_to_object_property">
		<xsl:with-param name="verb" select="VP/VBZ" />
	</xsl:call-template>) 
        <xsl:text> </xsl:text>
	<xsl:call-template name="noun_to_individual">
            <xsl:with-param name="noun" select="NN|NNP" />
        </xsl:call-template>))
</xsl:template>

<xsl:template match="S[count(S)=0][VP/VBZ/@id=@root and NP[*[@entity]] and VP[NP[count(*[@entity])=0]]]">
SubClassOf(
	<xsl:call-template name="noun_phrase_to_class">
		<xsl:with-param name="NP" select="VP/NP" />
	</xsl:call-template>
	<xsl:text> </xsl:text>
	ObjectHasValue(ObjectInverseOf(
        <xsl:call-template name="verb_to_object_property">
		<xsl:with-param name="verb" select="VP/VBZ" />
	</xsl:call-template>) 
        <xsl:text> </xsl:text>
	<xsl:call-template name="noun_phrase_to_individual">
            <xsl:with-param name="NP" select="NP" />
        </xsl:call-template>))
</xsl:template>

</xsl:stylesheet>